<template>
  <div class="container">
    <h1 class="title">
      My To Do List
    </h1>
    <to-do-list :todos="todos" />
  </div>
</template>

<script>

import ToDoList from '~/components/ToDoList.vue'
export default {
  components: {
    ToDoList
  },
  data () {
    return {
      todos: {
        type: Array
      }
    }
  },
  async asyncData (ctx) {
    return {
      todos: await ctx.app.$services.todo.findAll()
    }
  }
}
</script>
