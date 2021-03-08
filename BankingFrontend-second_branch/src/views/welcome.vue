<template>
<div>
    <navbar />
    <sidebar />
    <div class="msg">
    <h1> </h1>
    <!-- <h1>{{id}}</h1> -->
    </div>
 <!-- <div class="flex-container">
 <div>
 <button v-on:click="deposit">Deposit</button>
 </div>
 <div>
 <button  v-on:click="transfer">Tranfer</button>
 </div>
 <div>
 <button  v-on:click="withdrawal">Withdraw</button>
 </div>
 <div>
 <button  v-on:click="past_transaction">Past Transactions</button>
 </div>
 <div>
 <button  v-on:click="account">Account Details</button>
 </div>
</div> -->
<div class="container">
   <b> Creating Account is Just 2 Steps Away - You'll like it here.</b>
    <button class="submitbtn" v-on:click="createAccount">Create Account</button>
</div>
<span>
</span>
<marquee class="heading" behaviour="scroll" direction="right">Welcome to Quinpay - Connecting all your banking needs.</marquee>
<Footer />
</div>
</template>
<script>
// import axios from 'axios'
import navbar from '@/components/navbar.vue'
import footer from '@/components/footer.vue'
import axios from 'axios'
import sidebar from '@/components/sidebar.vue'
export default {
  name: 'welcome',
  data () {
    return {
      jwt: '',
      uname: '',
      pin: '**'
    }
  },
  components: {
    navbar: navbar,
    Footer: footer,
    sidebar: sidebar
  },
  mounted () {
    this.id = localStorage.getItem('jwt')
    if (localStorage.getItem('jwt') === null) {
      this.$router.push('/login')
    }
  },
  // this.email = localStorage.getItem('email')
  // axios.get('http://10.177.68.51:9090/login-as-admin ', ob)
  // .then((res) => {
  // this.id = res.data.email
  // })
  //     console.log(this.jwt)
  //   },
  methods: {
    // deposit () {
    //   this.$router.push('/deposit')
    // },
    // transfer () {
    //   this.$router.push('/transfer')
    // },
    // withdrawal () {
    //   this.$router.push('/withdraw')
    // },
    // past_transaction () {
    //   this.$router.push('/transactiondetails')
    // },
    // account () {
    //   this.$router.push('/accountdetails')
    // },
    createAccount () {
      this.$alert('Are You Sure ? For creating an account you are required to set a PIN')
      // setTimeout(2000)
      this.$prompt('Please Create Your Secret PIN').then((text) => {
        // do somthing with text
        this.pin = text
        var obj = {
          pin: this.pin
        }
        // eslint-disable-next-line valid-typeof
        if (typeof (obj.pin) == null) {
          console.log(obj.pin)
          alert('Please Enter the PIN')
        } else {
          this.jwt = localStorage.getItem('jwt')
          axios.post('http://10.177.68.59:8080/transaction-service/account/createAccount', obj, {
            headers: {
              Authorization: 'Bearer ' + this.jwt
            }
          }).then((result) => {
            console.log(result)
            this.results = result.data
            console.log(this.results.message)
            if (this.results.message === 'Account created Successfully') {
              // Show withdraw details,Success message and redirect to welcome page
              this.$alert('Account Created Successfully!!')
              this.$alert('Account has been created !!' + '\n' + 'Your Current Balance is: ' + this.results.accountBalance + ' Your Account Number is: ' + this.results.accountNo)
              this.$router.push({ name: 'welcome' })
            } else {
              // console.log(this.results)
              // this.$alert('Pin NOT set')
              // Show that the account no to which money is being transferred does not exist and stay in the same page
            }
          // this.$alert('Account has been created' + '\n' + 'CurrentBalance' + this.results.accountBalance)
          //   this.details = JSON.parse(localStorage.getItem('details'))
          //   console.log('details fetched')
          //   console.log(this.details)
          })
            .catch((error) => {
              console.log(error)
            })
        }
      })
    }
  }
}
</script>
<style scoped>
/* .flex-container {
    margin-top:40px;
 display: flex;
 flex-direction: row;
 max-height:500px;
 background-color: #31708E;
}
.flex-container > div {
 background-color: #f1f1f1;
 height:300px;
 width:300px;
 margin: 30px;
 margin-bottom:30px;
 padding: 30px;
 font-size: 30px;
} */
.heading {
  position:fixed;
 margin-top: 500px;
 margin-bottom:100px;
 font-size:40px;
 color:#687864;
}
.msg {
    margin-top:75px;
    color:whitesmoke;
    height:100px;
    display:flex;
    align-content: center;
}
 .submitbtn {
     margin-top: 30px;
     width: auto;
  padding: 10px 18px;
  background-color: #5085A5;
  font-family: 'Vollkorn', serif;
}
.container {
  border: 10px solid #f1f1f1;
  width: 30%;
  align-content: center;
  justify-items: center;
  margin-left:500px;
  margin-top:140px;
  /* margin-left:350px;
  margin-bottom: 100px; */
  padding: 16px;
  display:flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  opacity:0.8;
  background-color: rgb(192, 175, 192);
  border-radius: 10px;
  position: fixed;
}
</style>
