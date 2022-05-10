<template>
  <h1>Hello</h1>
  <v-col cols="12" sm="6" md="3">
    <v-text-field style="min-width = 200px;" label="Filled" placeholder="Placeholder" filled></v-text-field>
  </v-col>
  <MarvelCharacter />
</template>

<script>
import MarvelCharacter from "./components/MarvelCharacter.vue";

export default {
  name: "App",
  data() {
    return {
      inputText: null,
    };
  },
  components: {
    MarvelCharacter,
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
