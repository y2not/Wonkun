<template>
  <div id="chart-sample">
    <v-container class="lighten-5">
      <v-snackbar v-model="snackbar">{{ errorMessage }}</v-snackbar>
      <div v-if="loading" class="loading">
        🔧 Building Charts ...
        <div class="sk-cube-grid">
          <div class="sk-cube sk-cube1"></div>
          <div class="sk-cube sk-cube2"></div>
          <div class="sk-cube sk-cube3"></div>
          <div class="sk-cube sk-cube4"></div>
          <div class="sk-cube sk-cube5"></div>
          <div class="sk-cube sk-cube6"></div>
          <div class="sk-cube sk-cube7"></div>
          <div class="sk-cube sk-cube8"></div>
          <div class="sk-cube sk-cube9"></div>
        </div>
      </div>

      <package-info
        :package-name="packageName"
        :total-downloads="totalDownloads"
        :period="formattedPeriod"
        v-if="loaded"
      />
      <div class="Chart__container" v-if="loaded">
        <div class="Chart__title">
          <h2>
            Downloads per Day
            <span>{{ formattedPeriod }}</span>
          </h2>
          <DownloadButton :name="packageName + '-daily'" :link="dailyPng" />
        </div>
        <hr />
        <div class="Chart__content">
          <line-chart
            chart-id="line-daily"
            v-if="loaded"
            :chart-data="downloads"
            :chart-labels="labels"
            @generate="setDailyPng"
          />
        </div>
      </div>

      <div class="Chart__container" v-if="loaded">
        <div class="Chart__title">
          <h2>
            Downloads per Week
            <span>{{ formattedPeriod }}</span>
          </h2>
          <DownloadButton :name="packageName + '-weekly'" :link="weeklyPng" />
        </div>
        <hr />
        <div class="Chart__content">
          <line-chart
            chart-id="line-weekly"
            v-if="loaded"
            :chart-data="downloadsWeek"
            :chart-labels="labelsWeek"
            @generate="setWeeklyPng"
          />
        </div>
      </div>

      <div class="Chart__container" v-if="loaded">
        <div class="Chart__title">
          <h2>
            Downloads per Month
            <span>{{ formattedPeriod }}</span>
          </h2>
          <DownloadButton :name="packageName + '-monthly'" :link="monthlyPng" />
        </div>
        <hr />
        <div class="Chart__content">
          <line-chart
            v-if="loaded"
            chart-id="line-monthly"
            :chart-data="downloadsMonth"
            :chart-labels="labelsMonth"
            @generate="setMonthlyPng"
          />
        </div>
      </div>

      <div class="Chart__container" v-if="loaded">
        <div class="Chart__title">
          <h2>
            Downloads per Year
            <span>{{ formattedPeriod }}</span>
          </h2>
          <DownloadButton :name="packageName + '-yearly'" :link="yearlyPng" />
        </div>
        <hr />
        <div class="Chart__content">
          <bar-chart
            v-if="loaded"
            chart-id="bar-yearly"
            :chart-data="downloadsYear"
            :chart-labels="labelsYear"
            @generate="setYearlyPng"
          />
        </div>
      </div>
    </v-container>
  </div>
</template>

<script>
import LineChart from "@/components/templates/LineChart";
import BarChart from "@/components/templates/BarChart";
import PackageInfo from "@/components/templates/PackageInfo";
import DownloadButton from "@/components/templates/Download";

import {
  dateToYear,
  dateToMonth,
  dateToWeek,
  dateToDay,
  dateBeautify
} from "@/utils/dateFormatter";

import { removeDuplicate, groupData } from "@/utils/downloadFormatter.js";

export default {
  components: {
    LineChart,
    BarChart,
    PackageInfo,
    DownloadButton
  },
  data() {
    return {
      snackbar: false,
      errorMessage: "Error has occurred during retreiving data...",
      loading: false,
      loaded: false,
      showSettings: false,
      rawData: "",
      downloads: [],
      labels: [],
      packageName: "",
      totalDownloads: "",
      periodStart: "",
      periodEnd: new Date(),
      dailyPng: null,
      weeklyPng: null,
      monthlyPng: null,
      yearlyPng: null,
      package: "vue.js"
    };
  },
  mounted() {
    this.requestData();
  },
  computed: {
    formattedPeriod() {
      return this.periodStart
        ? `${dateBeautify(this._startDate)} - ${dateBeautify(this._endDate)}`
        : "last-month";
    },
    _endDate() {
      return dateToDay(this.periodEnd);
    },
    _startDate() {
      return dateToDay(this.periodStart);
    }
  },

  methods: {
    resetState() {
      this.loaded = false;
      this.snackbar = false;
    },
    requestData() {
      this.resetState();
      this.loading = true;
      this.$http
        .get(`https://api.npmjs.org/downloads/range/last-month/vue.js`)
        .then(function(response) {
          return response.json();
        })
        .then(function(response) {
          //   console.log(response);
          this.rawData = response.downloads;
          this.downloads = response.downloads.map(entry => entry.downloads);
          this.labels = response.downloads.map(entry => entry.day);
          this.packageName = response.package;
          this.totalDownloads = this.downloads.reduce(
            (total, download) => total + download
          );
          this.setURL();
          this.groupDataByDate();
          this.loaded = true;
          this.loading = false;
        })
        .catch(function(response) {
          this.errorMessage = response.body.error
            ? response.body.error
            : this.errorMessage;
          this.snackbar = true;
          this.loading = false;
        });
    },
    groupDataByDate() {
      this.formatYear();
      this.formatMonth();
      this.formatWeek();
    },
    formatYear() {
      this.labelsYear = this.rawData
        .map(entry => dateToYear(entry.day))
        .reduce(removeDuplicate, []);
      this.downloadsYear = groupData(this.rawData, dateToYear);
    },
    formatMonth() {
      this.labelsMonth = this.rawData
        .map(entry => dateToMonth(entry.day))
        .reduce(removeDuplicate, []);
      this.downloadsMonth = groupData(this.rawData, dateToMonth);
    },
    formatWeek() {
      this.labelsWeek = this.rawData
        .map(entry => dateToWeek(entry.day))
        .reduce(removeDuplicate, []);
      this.downloadsWeek = groupData(this.rawData, dateToWeek);
    },

    setURL() {
      this.$router.push({
        name: "Package",
        params: {
          package: this.package
        }
      });
    },
    setDailyPng(payload) {
      this.dailyPng = payload;
    },
    setWeeklyPng(payload) {
      this.weeklyPng = payload;
    },
    setMonthlyPng(payload) {
      this.monthlyPng = payload;
    },
    setYearlyPng(payload) {
      this.yearlyPng = payload;
    }
  }
};
</script>

<style scoped>
.sk-cube-grid {
  width: 40px;
  height: 40px;
  margin: 100px auto;
}
.sk-cube-grid .sk-cube {
  width: 33%;
  height: 33%;
  background-color: color(robin-egg-blue);
  float: left;
  -webkit-animation: sk-cubeGridScaleDelay 1.3s infinite ease-in-out;
  animation: sk-cubeGridScaleDelay 1.3s infinite ease-in-out;
}
.sk-cube-grid .sk-cube1 {
  -webkit-animation-delay: 0.2s;
  animation-delay: 0.2s;
}
.sk-cube-grid .sk-cube2 {
  -webkit-animation-delay: 0.3s;
  animation-delay: 0.3s;
}
.sk-cube-grid .sk-cube3 {
  -webkit-animation-delay: 0.4s;
  animation-delay: 0.4s;
}
.sk-cube-grid .sk-cube4 {
  -webkit-animation-delay: 0.1s;
  animation-delay: 0.1s;
}
.sk-cube-grid .sk-cube5 {
  -webkit-animation-delay: 0.2s;
  animation-delay: 0.2s;
}
.sk-cube-grid .sk-cube6 {
  -webkit-animation-delay: 0.3s;
  animation-delay: 0.3s;
}
.sk-cube-grid .sk-cube7 {
  -webkit-animation-delay: 0s;
  animation-delay: 0s;
}
.sk-cube-grid .sk-cube8 {
  -webkit-animation-delay: 0.1s;
  animation-delay: 0.1s;
}
.sk-cube-grid .sk-cube9 {
  -webkit-animation-delay: 0.2s;
  animation-delay: 0.2s;
}
.Chart__container {
  border-radius: 0.25rem;
  background-color: #fff;
  box-shadow: 0 15px 30px 0 rgba(236, 227, 227, 0.11),
    0 5px 15px 0 rgba(243, 240, 240, 0.08);
  padding: 1.25rem 2.5rem;
  margin: 3.125rem 0;
}

.Chart__title {
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  flex-direction: row;
  margin-bottom: 1.25rem;
  -webkit-box-pack: justify;
  justify-content: space-between;
}

.Chart__title h2 {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  color: #4f5566;
  margin: 0;
  font-weight: 600;
  font-size: 1rem;
  font-family: Source Sans Pro, Helvetica Neue, Helvetica, Arial, sans-serif;
}

.Chart__download {
  cursor: pointer;
  border: 1px solid #f1f1f1;
  border-radius: 0.3125rem;
  padding: 0.3125rem;
  text-decoration: none;
}
.Chart__download:hover {
  border-color: #00c4c9;
}
</style>