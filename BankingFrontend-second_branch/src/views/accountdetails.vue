<template>
  <div cass="main">
      <navbar />
      <sidebar />
    <div class="container">
      <table style="width:100%">
              <tr>
            <th>Sr.No.</th>
            <th>Account Number</th>
            <th>Account Balance</th>
        </tr>
        <tr v-for = '(res, index) in results' :key="res.id">
      <td>{{index+1}}</td>
      <td>{{res.accountNo}}</td>
      <td>{{res.accountBalance}}</td>
      </tr>
      </table>
      <Footer />
  </div>
  </div>
</template>

<script>
import navbar from '@/components/navbar.vue'
import footer from '@/components/footer.vue'
import axios from 'axios'
import sidebar from '@/components/sidebar.vue'
export default {
  name: 'accountdetails',
  data () {
    return {
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
    // this.jwt = localStorage.getItem('jwt')
    if (localStorage.getItem('jwt') === null) {
      this.$router.push('/login')
    }
    this.jwt = localStorage.getItem('jwt')
    axios.get('http://10.177.68.59:8080/transaction-service/account/getAccounts', {
      headers: {
        Authorization: 'Bearer ' + this.jwt
        // 'Access-Control-Allow-Origin': '*', // Required for CORS support to work
        // 'Access-Control-Allow-Credentials': true
      }
    }).then((result) => {
      console.log(result)
      console.log('success')
      //   localStorage.setItem('details', result.data)
      this.results = result.data
      //   this.details = JSON.parse(localStorage.getItem('details'))
      //   console.log('details fetched')
      //   console.log(this.details)
    })
      .catch((error) => {
        console.log(error)
      })
  }

}
</script>

<style>
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
  font-style: Arial;
}
input[type=text], input[type=password],input[type=email] {
  width: 50%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 2px solid rgb(8, 8, 8);
  box-sizing: border-box;
}
button:hover {
  opacity: 0.6;
}
.submitbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #5085A5;
}
table {
  font-family: 'Vollkorn', serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}
</style>
