<template>
    <div class="main">
        <navbar />
        <sidebar />
        <h1>Welcome to deposit page</h1>
        <div class="container">
        <label for="Amount">Amount</label>
        <input type='text' v-model='amount' id='Amount'>
        <label for="To">Account No</label>
        <input type='text' v-model='accountNo' id='AccountNo'>
        <!-- <label for="From">Pin</label>
        <input type='password' v-model='pin' id='FromAccountNo'> -->
        <button type="submit" class="submitbtn" @click="doDeposit">Deposit</button>
        </div>
        <!-- Modal -->
        <Footer />
    </div>
</template>

<script>
import navbar from '@/components/navbar.vue'
import footer from '@/components/footer.vue'
import axios from 'axios'
import sidebar from '@/components/sidebar.vue'

// // Get the modal
// var modal = document.getElementById('myModal')

// // Get the button that opens the modal
// var btn = document.getElementById('myBtn')

// // Get the <span> element that closes the modal
// var span = document.getElementsByClassName('close')[0]

// // When the user clicks the button, open the modal
// btn.onclick = function () {
//   modal.style.display = 'block'
// }

// // When the user clicks on <span> (x), close the modal
// span.onclick = function () {
//   modal.style.display = 'none'
// }

// // When the user clicks anywhere outside of the modal, close it
// window.onclick = function (event) {
//   if (event.target === modal) {
//     modal.style.display = 'none'
//   }
// }
export default {
  name: 'deposit',
  data () {
    return {
      pin: '',
      accountNo: '',
      amount: '',
      jwt: '',
      results: [],
      pin2: ''
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
      if (r.message === 'Account Number does not Exist' || r.message === 'Invalid input for deposit amount!' || r.message === 'Invalid Pin') {
        console.log(r.message)
        return r.message
      } else {
        return 'Success'
      }
    },
    doDeposit () {
      var obj = {
        pin: '',
        accountNo: this.accountNo,
        amount: this.amount
        // user_id: localStorage.getItem('user_id')
      }
      if (this.amount === '') {
        this.$alert('Enter the Amount')
      } else if (this.accountNo === '') {
        this.$alert('Enter Your Account Number')
      } else {
        console.log(false)
        this.$prompt('Input your Secret PIN').then((text) => {
        // do somthing with text
          obj.pin = text
          this.jwt = localStorage.getItem('jwt')
          console.log(this.jwt)
          axios.put('http://10.177.68.59:8080/transaction-service/transaction/deposit', obj, {
            headers: {
              Authorization: 'Bearer ' + this.jwt
            }
          })
            .then((result) => {
              console.log(result)
              this.results = result.data
              //  this.$alert(this.validate(this.results))
              if (this.validate(this.results) === 'Success') {
                var t = 'Your Current Balance is : ' + this.results.currentBalance + '\n' + 'Your transaction was Successful'
                this.$alert(t)
                this.$router.push({ name: 'welcome' })
              } else {
              // console.log(this.results)
                this.$alert('Re Enter Account Details')
              // Show that the account no to which money is being transferred does not exist and stay in the same page
              }
            // this.$router.push({ name: 'welcome' })
              // localStorage.setItem('email', this.posts.email)
            })
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
  /* margin-top:140px;
  margin-left:350px;
  margin-bottom: 100px; */
  margin: auto;
  margin-top:140px;
  padding: 16px;
  display:flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  opacity:0.8;
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
