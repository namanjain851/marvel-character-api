<template>
  <div >
    <template v-for="character in characterInfo">
      <h6>{{ character.id }} {{ character.name }}</h6>
      <h6>{{ character.description }}</h6>
      <img style="width:200px;height:200px;" :src="character.thumbnail.path + '.'+character.thumbnail.extension">
    </template>
  </div>
</template>

<script>
export default {
  name: 'MarvelCharacter',
  props: {
    characterInfo: {type:Object,required:true}
  }
}
</script>
