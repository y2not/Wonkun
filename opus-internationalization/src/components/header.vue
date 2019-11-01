<template>
  <nav>
    <ul>
      <h2>OPUS Internationalization Component Generator</h2>
      <li>
        <router-link to="/" exact>Home</router-link>
      </li>
      <li>
        <router-link to="/labels">Show components</router-link>
      </li>
      <li>
        <router-link to="/label/add" exact>Add a new component</router-link>
      </li>
      <li>
        <router-link to="/messages">Show messages</router-link>
      </li>
      <li>
        <router-link to="/message/add" exact>Add a new messsage</router-link>
      </li>
      <li>
        <router-link to="/showInternalization" exact>Show predefine internalization</router-link>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {};
</script>

<style scoped>
ul {
  list-style-type: none;
  text-align: center;
  margin: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #fff;
  text-decoration: none;
  padding: 12px;
  border-radius: 5px;
}
h2 {
  text-align: center;
}
nav {
  background: crimson;
  padding: 30px 0;
  margin-bottom: 40px;
}
.router-link-active {
  background: rgba(255, 255, 255, 0.8);
  color: #444;
}
</style>
