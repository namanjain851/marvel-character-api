<template>
  <h1>Hello KOLO</h1>
  <input type="text" v-model="inputText"/>
  <button v-on:click="getData">Submit</button>
  <p v-show = "loading"> Loading...</p>
  <button v-on:click="prevPage" :disabled = "pageNo === 1">Prev Page</button>
  <button v-on:click="nextPage" :disabled = "next_total <= 0">Next Page</button>
  <MarvelCharacter 
    :characterInfo = "response"
  />
</template>

<script>
import MarvelCharacter from "./components/MarvelCharacter.vue";
const axios = require('axios');
axios.defaults.baseURL = "http://localhost:8080";
export default {
  name: "App",
  data() {
    return {
      inputText: null,
      loading: false,
      pageNo: 1,
      response: null,
      next_total: 0,
    };
  },
  components: {
    MarvelCharacter,
  },  
  methods:{
    getData :function(){
      var self = this;
      this.loading = true;
      axios.get('/api/'+this.inputText,{
        params:{
          page:this.pageNo
        }
      }).then(function(response){
          self.response = response.data.data.results;
          self.loading = false;
          self.next_total = response.data.data.total - (self.pageNo*10);
      })
    },
    prevPage(){
      this.pageNo  = this.pageNo - 1;
      this.getData();
    },
    nextPage(){
      this.pageNo = this.pageNo + 1;
      this.getData();
    }
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
