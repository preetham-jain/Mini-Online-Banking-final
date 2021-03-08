<template>
  <div class="sidebar">
      <div>
      <button class="button1" v-on:click="account">Account Details</button>
      </div>
      <div>
      <button class="button1" v-on:click="deposit">Deposit</button>
      </div>
      <div>
      <button class="button1" v-on:click="transfer">Tranfer</button>
      </div>
      <div>
      <button class="button1" v-on:click="withdrawal">Withdraw</button>
      </div>
      <div>
      <button class="button1" v-on:click="past_transaction">Past Transactions</button>
      </div>
  </div>
</template>

<script>
export default {
  name: 'sidebar',
  methods: {
    deposit () {
      this.$router.push('/deposit')
    },
    transfer () {
      this.$router.push('/transfer')
    },
    withdrawal () {
      this.$router.push('/withdraw')
    },
    past_transaction () {
      this.$router.push('/transactiondetails')
    },
    account () {
      this.$router.push('/accountdetails')
    }
  }
}
</script>

<style scoped>
.sidebar {
  position: fixed;
  width: 250px;
  bottom: 105px;
  top: 70px;
  left: 0px;
  /* background-color: white; */
  z-index: 2;
  background-color:rgb(120, 207, 248);
  opacity: 0.7;
  /* overflow-x: hidden; */
  display:flex;
  flex-direction:column;
  justify-content: space-evenly;
}
/* .button1{
  width: 250px;
  padding: 10px 18px;
  background-color: #5085A5;
} */
.button1 {
  width: 250px;
  padding: 10px 18px;
  background-color:#31708E;
  /* flex: 1 1 auto; */
  text-align: center;
  text-transform: uppercase;
  transition: 0.5s;
  background-size: 200% auto;
 text-shadow: 0px 0px 10px rgba(0,0,0,0.2);
  box-shadow: 0 0 10px rgb(10, 12, 15);
  border-radius: 10px;
  font-family: 'Vollkorn', serif;
 }

/* Demo Stuff End -> */

/* <- Magic Stuff Start */

.btn:hover {
  background-position: right center; /* change the direction of the change here */
}

.btn-1 {
  background-image: linear-gradient(to right, #f6d365 0%, #fda085 51%, #f6d365 100%);
}
</style>
