<template>
 <div>
   <navbar />
  <div class="container">
    <div class="container1">
    <label for="fname"><b>First Name</b></label>
    <input type="text" id="fname" placeholder="First Name" v-model="firstName" >
    <label for="lname"><b>Last Name</b></label>
    <input type="text" id="lname" placeholder="Last Name" v-model="lastName" >
     </div>
    <label for="phone"><b>Phone Number</b></label>
    <input type="text" v-model="phone_no"/>
    <label for="address"><b>Address</b></label>
    <textarea cols="80" rows="5" value="address" v-model="address"></textarea>
    <label for="email"><b>Email</b></label>
    <input type="email" id="email" placeholder="E-mail" v-model="email">
    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password"  v-model="password" required>
    <label for="psw"><b>Confirm Password</b></label>
    <input type="password" placeholder="Confirm Password" v-model="cpassword" required>
    <div style="background-color:#f1f1f1">
    <button type="submit" class="submitbtn" @click="postData">Submit</button>
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
  name: 'register',
  data () {
    return {

      firstName: '',
      lastName: '',
      phone_no: '',
      address: '',
      email: '',
      password: '',
      cpassword: ''
    }
  },
  components: {
    navbar: navbar,
    Footer: footer
  },
  methods: {
    validate () {
      if (this.firstName === '') {
        this.$alert('First Name cann\'t be Empt')
      }
      if (this.lastName === '') {
        this.$alert('Last Name cann\'t be Empty')
        return 0
      }
      if (isNaN(this.phone_no) || this.phone_no === '' || this.phone_no.length !== 10) {
        this.$alert('Invalid Phone number, must be 10 digits')
        return 0
      }
      if (this.email === '') {
        this.$alert('Email cann\'t be Empty')
        return 0
      }
      if (this.password === '' || (this.password.length < 8 || this.password.length > 16)) {
        this.$alert('Invalid password, must be between than 8 to 16 characters')
        return 0
      }
      if (this.password !== this.cpassword) {
        this.$alert('Password does not match')
        return 0
      }
      return 1
    },
    postData () {
      const obj = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        phone_no: this.phone_no,
        password: this.password,
        address: this.address
      }
      if (this.validate()) {
        axios.post('http://10.177.68.59:8080/loginservice/register', obj).then((result) => {
          console.log(result)
          this.$router.push('/login')
        })
      }
    }
  }
}
</script>

<style scoped>
form {
  border: 10px solid #f1f1f1;
  width: 50%;
  align-content: center;
  justify-items: center;
  margin-top:140px;
  margin-left:350px;
  margin-bottom: 200px;
}
input[type=text], input[type=password],textarea, input[type=email] {
  width: 50%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
  opacity:1;
}
#name {
    width: 20%;
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
  font-family: 'Vollkorn', serif;
}

.container {
  border: 5px solid #f1f1f1;
  width: 50%;
  align-content: center;
  justify-items: center;
  margin-top:140px;
  margin-left:350px;
  margin-bottom: 150px;
  padding: 16px;
  display:flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  background-color: rgb(192, 175, 192);
  opacity:0.7;
  border-radius: 10px;
  /* background : rgba(0, 151, 19, 0.3); */

}
.container1 {
  padding: 16px;
  display:flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  opacity: 2;
}
input[type="text"] {
  margin-right: 12px;
}
</style>
