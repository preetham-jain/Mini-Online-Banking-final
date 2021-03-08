<template>
  <div>
      <navbar />
      <sidebar />
      <h1>Welcome to transfer page</h1>
        <div class="container">
        <label for="Amount">Amount</label>
        <input type='text' v-model='amount' id='Amount'>
        <label for="Sender">Sender Account No</label>
        <input type='text' v-model='senderAccount' id='SenderAccountNo'>
        <label for="Receiver">Receiver Account No</label>
        <input type='text' v-model='recipientAccount' id='ReceiverAccountNo'>
        <!-- <label for="Pin">Pin</label>
        <input type='password' v-model='pin' id='pin'> -->
        <button type="submit" class="submitbtn" @click="doTransfer">Transfer</button>
        </div>
        <Footer />
</div>
</template>

<script>
import axios from 'axios'
import navbar from '@/components/navbar.vue'
import footer from '@/components/footer.vue'
import sidebar from '@/components/sidebar.vue'
export default {
  name: 'transfer',
  data () {
    return {
      senderAccount: '',
      recipientAccount: '',
      amount: '',
      pin2: '',
      results: '',
      jwt: '',
      pin: ''
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
      if (r === 'Sender account does not exist' || r === 'Recipient account does not exist' || r === 'Insufficient Balance' || r === 'Invalid input for transfer amount!' || r === 'Invalid pin') {
        console.log(r)
        return r
      } else {
        return 'Success'
      }
    },
    doTransfer () {
      var obj = {
        senderAccount: this.senderAccount,
        recipientAccount: this.recipientAccount,
        amount: this.amount,
        pin: ''
        // user_id: localStorage.getItem('user_id')
      }
      this.jwt = localStorage.getItem('jwt')
      if (this.amount === '') {
        this.$alert('Enter the Amount')
      } else if (this.senderAccount === '') {
        this.$alert('Enter Your Account Number')
      } else if (this.recipientAccount === '') {
        this.$alert('Enter Recipient Account Number')
      } else if (this.senderAccount === this.recipientAccount) {
        console.log(true)
        this.$alert('Transaction can only be done betweeen different user accounts')
        this.senderAccount = ''
        this.recipientAccount = ''
        // this.$router.push({ name: 'transfer' })
      } else {
        console.log(false)
        this.$prompt('Input your Secret PIN', 'PIN', '', 'info').then((text) => {
        // do somthing with text
          console.log('pinnn' + text)
          obj.pin = text

          axios.put('http://10.177.68.59:8080/transaction-service/transaction/transfer', obj, {
            headers: {
              Authorization: 'Bearer ' + this.jwt
            }
          })
            .then((result) => {
              this.results = result.data
              console.log(result)
              // validation of result
              // console.log('testing phase', this.validate(result.data), this.validate(result.data) === 'Success')
              if (this.validate(this.results) === 'Success') {
                // Show withdraw details,Success message and redirect to welcome page
                this.$alert('Success')
                this.$router.push({ name: 'welcome' })
              } else {
                // console.log(this.results)
                this.$alert('Re enter Sender and Receiver Account No')
                // Show that the account no to which money is being transferred does not exist and stay in the same page
              }
            // this.$router.push({ name: 'Welcome' })
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
