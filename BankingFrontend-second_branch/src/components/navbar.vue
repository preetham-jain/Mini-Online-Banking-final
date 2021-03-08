<template>
<div>
    <nav class="navbar">
    <span v-if="state !== null">
    <button class="links" id="b3" @click="logout" tag="button">Logout</button>
     <router-link class="links" to="/welcome" id="b1" tag="button">Home</router-link>
    </span>
    <span v-else>
    <router-link class="links" to="/" id="b1" tag="button">Home</router-link>
    <router-link class="links" to="/login" id="b2" tag="button">Login</router-link>
    <router-link class="links" to="/register" id="b3" tag="button">Register</router-link>
    </span>
  </nav>
</div>
</template>

<script>
export default {
  data () {
    return {
      state: localStorage.getItem('jwt')
    }
  },
  methods: {
    logout () {
      console.log('Logout called')
      localStorage.removeItem('jwt')
      this.$router.push('/login')
    }
  }

}
</script>

<style>
.navbar {
    padding:0px;
    height: 70px;
    position:fixed;
    background-color:#8FC1E3;
    top:0;
    width: 100%;
    left:0;
}
.links:hover {
  background-color:#31708E; /* Green */
  color: white;
}
.buttons {
  border-radius: 12px;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: black;
  display: inline-block;
  font-size: 16px;
  margin: 14px 12px;
  cursor: pointer;
}
.links {
  border-radius: 12px;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: black;
  display: inline-block;
  font-size: 16px;
  margin: 14px 12px;
  cursor: pointer;
  text-align: center;
  text-decoration:none ;
  color:black;
}
#b1{
  float:left;
  margin: 14px 102px;
  margin-left: 10px;
}
#b2{
  float: right;
  margin: 14px 10px;
}
#b3{
  float:right;
  margin: 14px 10px;
  margin-left: 452px;
}

</style>
