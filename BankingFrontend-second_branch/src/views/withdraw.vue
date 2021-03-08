<template>
<div class="main">
    <navbar />
    <sidebar />
 <h1>Welcome to withdraw page</h1>
 <div class="container">
 <label for="Amount">Amount</label>
 <input type='text' v-model='amount' id='Amount'>
 <label for="To">Account No</label>
 <input type='text' v-model='accountNo' id='AccountNo'>
 <!-- <label for="From">Pin</label>
 <input type='password' v-model='pin' id='password'> -->
 <button type="submit" class="submitbtn" @click="doWithdraw">Withdraw</button>
 </div>
 <Footer />
</div>
</template>

<script>
import axios from 'axios'
import footer from '@/components/footer.vue'
import navbar from '@/components/navbar.vue'
import sidebar from '@/components/sidebar.vue'
export default {
  name: 'withdraw',
  data () {
    return {
      pin: '',
      accountNo: '',
      amount: '',
      jwt: '',
      results: []
    }
  },
  components: {
    navbar: navbar,
    Footer: footer,
    sidebar: sidebar
  },
  mounted () {
    if (localStorage.getItem('jwt') === null) {
      this.$router.push('/login')
    }
  },
  methods: {
    validate (r) {
      console.log(r)
      console.log('Validate called')
      if (r.message === 'Account Number does not Exist' || r.message === 'InSufficient Balance' || r.message === 'Invalid input for withdrawal amount!' || r.message === 'Invalid Pin') {
        console.log(r.message)
        return r.message
      } else {
        return 'Success'
      }
    },
    doWithdraw () {
      var obj = {
        pin: '',
        accountNo: this.accountNo,
        amount: this.amount
      }
      if (this.amount === '') {
        this.$alert('Enter the Amount')
      } else if (this.accountNo === '') {
        this.$alert('Enter Your Account Number')
      } else {
        this.$prompt('Input your Secret PIN', 'PIN', '', 'info', 'cancelme').then((text) => {
        // do somthing with text
          obj.pin = text
          this.jwt = localStorage.getItem('jwt')
          axios.put('http://10.177.68.59:8080/transaction-service/account/withdraw', obj, {
            headers: {
              Authorization: 'Bearer ' + this.jwt
            }
          })
            .then((result) => {
              console.log(result)
              this.results = result.data
              // validation of result
              var c = this.validate(this.results)
              if (c === 'Success') {
                this.$alert(' Your Transaction was Successful!! ' + '  Your current Balance is  ' + this.results.currentBalance + '\n')
                this.$router.push('/welcome')
              } else {
                this.$alert(c)
                // Show that the account no to which money is being transferred does not exist and stay in the same page
              }
            })
            .catch(e => console.log(e))
        })
      }
    }
  }
}
</script>
<style scoped>
.container {
  border: 10px solid #f1f1f1;
  width: 30%;
  align-content: center;
  justify-items: center;
  margin:auto;
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
}
input[type=text], input[type=password],input[type=email] {
  width: 50%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}
button:hover {
  opacity: 0.6;
}
.submitbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #5085A5;
  font-family: 'Vollkorn', serif;
}
</style>
