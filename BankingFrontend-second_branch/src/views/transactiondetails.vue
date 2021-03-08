<template>
<div class="main">
  <navbar />
  <sidebar />
    <div class="container">
        <div>
        <input type="text" width="150" v-model="transactionType" placeholder ="Search"/>
        <button class="Search" @click="Search">Search</button>
        </div>
      <table style="width:100%">
           <tr>
               <th>Sr.No</th>
            <th>Id</th>
            <th>Date</th>
            <th>Sender's Account Number</th>
            <th>Reciever's Account Number</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Type</th>
        </tr>

        <!-- // eslint-disable-next-line vue/no-use-v-if-with-v-for
         v-if = 'index <= 20' -->
        <tr v-for = '(res, index) in results' :key="res.id" >
      <td>{{index+1}}</td>
      <td>{{res.user_id}}</td>
      <td>{{res.date}}</td>
      <td v-if= "res.myAccNo === null" >Null</td>
      <td v-else>{{res.myAccNo}}</td>
      <td v-if= "res.recipientAccNo === null" >Null</td>
      <td v-else>{{res.recipientAccNo}}</td>
      <td>{{res.amount}}</td>
      <td>{{res.status}}</td>
      <td>{{res.type}}</td>
      </tr>
      </table>
      <button class="btn" @click="generatePdf">Generate Pdf </button>
  </div>
   <Footer />
</div>
</template>

<script>
import navbar from '@/components/navbar.vue'
import footer from '@/components/footer.vue'
import axios from 'axios'
import jsPDF from 'jspdf'
import sidebar from '@/components/sidebar.vue'
export default {
  name: 'transactiondetails',
  components: {
    navbar: navbar,
    Footer: footer,
    sidebar: sidebar
  },
  data () {
    return {
      results: [],
      jwt: '',
      pdf: '',
      count: 0,
      transactionType: ''
    //   details: {
    //     userId: '',
    //     id: '',
    //     date: '',
    //     type: '',
    //     status: '',
    //     recepient_account_no: '',
    //     amount: ''
    //   }
    }
  },
  mounted () {
    if (localStorage.getItem('jwt') === null) {
      this.$router.push('/login')
    }
    this.jwt = localStorage.getItem('jwt')
    axios.get('http://10.177.68.59:8080/transaction-service/transaction/pastTransaction', {
      headers: {
        Authorization: 'Bearer ' + this.jwt
      }
    }).then((result) => {
      console.log(result)
      localStorage.setItem('details', result.data)
      this.results = result.data
      //   this.details = JSON.parse(localStorage.getItem('details'))
      //   console.log('details fetched')
      //   console.log(this.details)
    })
      .catch((error) => {
        console.log(error)
      })
  },
  methods: {
    Search () {
      var obj = {
        transactionType: this.transactionType
      }
      axios.post('http://10.177.68.59:8080/transaction-service/transaction/searchTransactionType', obj, {
        headers: {
          Authorization: 'Bearer ' + this.jwt
        }
      }).then((result) => {
        console.log(result)
        localStorage.setItem('details', result.data)
        this.results = result.data
      //   this.details = JSON.parse(localStorage.getItem('details'))
      //   console.log('details fetched')
      //   console.log(this.details)
      })
        .catch((error) => {
          console.log(error)
        })
    },
    generatePdf () {
      var count = 0
      var pdfName = 'transaction'
      let pdf = ''
      pdf = pdf + 'Sr.No' + ' ' + 'Id' + ' ' + 'Date' + ' ' + 'Sender Account Number' + ' ' + 'Reciever Account Number' + ' ' + 'Amount' + ' ' + 'Status' + ' ' + 'Type'
      for (const i in this.results) {
        console.log(this.results[i])
        const res = this.results[i]
        pdf = pdf + count + '. ' + ' '
        pdf = pdf + '    ' + res.user_id
        pdf = pdf + '    ' + res.date
        pdf = pdf + '    ' + res.myAccNo
        pdf = pdf + '    ' + res.recipientAccNo
        pdf = pdf + '    ' + res.amount
        pdf = pdf + '    ' + res.status
        pdf = pdf + '    ' + res.type
        pdf = pdf + '    ' + '\n'
        count++
      }
      // eslint-disable-next-line new-cap
      var doc = new jsPDF()
      doc.text(pdf, 10, 10)
      doc.save(pdfName + '.pdf')
    },
    increaseCount () {
      return this.count++
    }
  }
}
</script>

<style scoped>
.Search {
    display:inline-block;
    flex-direction: row;
    justify-content: space-between;
    border-radius: 20px;
    font-family: 'Vollkorn', serif;
}
.Search:hover {
    background-color:#5085A5;
    font-family: 'Vollkorn', serif;
}
.container {
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
  color: black;
  /* background-color: silver; */
  font-family: 'Akaya Telivigala', cursive;
font-family: 'Roboto', sans-serif;
}
table {
  font-family: 'Vollkorn', serif;
  /* font-family: arial, sans-serif; */
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}
button:hover {
  opacity: 0.8;
}
.btn {
  width: auto;
  padding: 10px 18px;
  background-color: #5085A5;
  font-family: 'Vollkorn', serif;
}
</style>
