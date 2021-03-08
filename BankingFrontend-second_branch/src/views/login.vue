<template>
<div class="main">
  <navbar />
  <div class="container">
    <label for="uname"><b>Email</b></label><br>
    <input type="email" placeholder="Enter Username" v-model="email" >
    <br>
    <label for="psw"><b>Password</b></label><br>
    <input type="password" placeholder="Enter Password" v-model="password">
    <div style="background-color:#f1f1f1">
    <button type="submit" class="submitbtn" @click="doLogin" >Submit</button>
  </div>
  </div>
  <Footer />
</div>
</template>

<script>
import footer from '@/components/footer.vue'
import navbar from '@/components/navbar.vue'
import axios from 'axios'
export default {
  name: 'login',
  data () {
    return {
      email: '',
      password: '',
      message: ''
    }
  },
  components: {
    navbar: navbar,
    Footer: footer
  },
  methods: {
    validate () {
      console.log('Hello I m stuck')
      if (this.email === '') {
        this.$alert('Email cannot be Empty')
        return 0
      }
      if (this.password === '') {
        this.$alert('Password cannot be Empty')
        return 0
      }
      return 1
    },
    doLogin () {
      console.log('Called')
      const obj = {
        email: this.email,
        password: this.password
      }
      if (this.validate()) {
        // localStorage.setItem('email', this.email)
        console.log('Redirect')
        axios.post('http://10.177.68.59:8080/loginservice/login', obj).then((result) => {
          console.log(result)
          if (result.data.message === 'Success') {
            console.log('Success')
            localStorage.setItem('jwt', result.data.jwt)
            this.$alert('You are successfully logged in')
            this.$router.push('/welcome')
          } else if (result.data.message === 'Incorrect password') {
            alert('Incorrect password')
          } else {
            alert('User does not exist')
          }
          // localStorage.setItem('id', result.data.id)
        })
        // eslint-disable-next-line handle-callback-err
          .catch((error) => {
            console.log(error)
          })
      }
    }
  }
}
</script>

<style scoped>
input[type=text], input[type=password],input[type=email] {
  width: 50%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}
button:hover {
  opacity: 0.8;
}
.submitbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #5085A5;
}

.container {
  /* padding: 16px;
  text-align: center;
  border: 10px solid #f1f1f1;
  width: 50%;
  align-content: center;
  justify-content: center;
  margin-top:140px;
  margin-left:350px; */
  border: 10px solid #f1f1f1;
  width: 50%;
  align-content: center;
  justify-items: center;
  margin-top:140px;
  margin-left:350px;
  margin-bottom: 100px;
  padding: 16px;
  display:flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  opacity:0.7;
  background-color: rgb(192, 175, 192);
  border-radius: 10px;
}

</style>
