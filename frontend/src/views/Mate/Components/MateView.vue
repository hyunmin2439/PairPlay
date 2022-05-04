<template>
  <div class="container">
    <p class="total-cards col-12">총 00 건의 검색결과</p>
    <div>
      <ul v-for="(card, i) in state.cards" :key="i">
        <li>
          <a class="card" href="#">
            <h3>{{card.title}}</h3>
            <p class="small">{{card.description}}</p>
          </a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { onMounted, reactive, computed } from 'vue';
import { useStore } from 'vuex';

export default {
  name: "MateView",
  setup() {
    const store = useStore()
    // let cards = [];
    const state = reactive({
      cards: computed(() => store.getters["root/mateArticleList"])
    })
    onMounted(() => {
      store.dispatch("root/mateArticleList")
    })
    // let expand = false;
    // const refresh = () =>
    // const changedExpand = () => {
    //   expand = !expand;
    //   console.log(expand, "expand");
    //   console.log(value, "value");
    //   return;
    // };

    return { state, onMounted };
  }
}
</script>

<style lang='scss' scoped>
  @import url('https://fonts.googleapis.com/css?family=Nunito:400,700');


  * {
    transition: all 0.3s ease-out;
  }

  html,
  body {
    height: 100%;
    font-family: "Nunito", sans-serif;
    font-size: 16px;
  }

  .container {
    width: 100%;
    height: 100%;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    // align-items: left;
    justify-content: center;
    // justify-content: left; 
  }

  h3 {
    color: #262626;
    font-size: 17px;
    line-height: 24px;
    font-weight: 700;
    margin-bottom: 4px;
  }

  p {
    font-size: 17px;
    font-weight: 400;
    line-height: 20px;
    color: #666666;

    &.small {
      font-size: 14px;
    }
  }

  // .go-corner {
  //   display: flex;
  //   align-items: center;
  //   justify-content: center;
  //   position: absolute;
  //   width: 32px;
  //   height: 32px;
  //   overflow: hidden;
  //   top: 0;
  //   right: 0;
  //   background-color: #00838d;
  //   border-radius: 0 4px 0 32px;
  // }

  // .go-arrow {
  //   margin-top: -4px;
  //   margin-right: -4px;
  //   color: white;
  //   font-family: courier, sans;
  // }

  .card {
    display: block;
    top: 0px;
    position: relative;
    max-width: 262px;
    background-color: #f2f8f9;
    border-radius: 4px;
    padding: 32px 24px;
    margin: 12px;
    text-decoration: none;
    z-index: 0;
    overflow: hidden;
    border: 1px solid #f2f8f9;

    &:hover {
      transition: all 0.2s ease-out;
      box-shadow: 0px 4px 8px rgba(38, 38, 38, 0.2);
      top: -4px;
      border: 1px solid #cccccc;
      background-color: white;
    }

    &:before {
      content: "";
      position: absolute;
      z-index: -1;
      top: -16px;
      right: -16px;
      background: #00838d;
      height: 32px;
      width: 32px;
      border-radius: 32px;
      transform: scale(2);
      transform-origin: 50% 50%;
      transition: transform 0.15s ease-out;
    }

    &:hover:before {
      transform: scale(2.15);
    }
  }

  .total-cards {
    text-align: right;
    margin-top: 1rem;
    margin-right: 8rem;
  }
</style>