<template>
  <div id="app">
      <router-view />
  </div>
</template>

<script>
// import navbar from '@/components/navbar.vue'
// import footer from '@/components/footer.vue'
export default {
  name: 'app'
}
</script>

<style lang="scss">
body {
  background-image:url('https://image.freepik.com/free-photo/hand-putting-money-coin-into-piggy-bank-isolated-grey-background-property-investment-house-mortgage-financial-concept_207927-188.jpg');
  background-repeat: no-repeat;
  background-size: cover;
  font-family: 'Vollkorn', serif;
  font-size: 15px;
// font-family: 'Roboto', sans-serif;
}
</style>
