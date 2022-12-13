import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    group : {
      groupCode	: '',
      groupDescription : '',
      groupId	: '',
      groupName	: '',
      groupOwnerId : '',
      dateCreated: '',
    },
    list : [],
    members : [{ 
      groupId : '',
      userId : '',
      username : '',
      dateJoined : ''
    }],
    items : [],
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_CURRENT_GROUP(state, data) {
      state.group = data;
    },
    SET_CURRENT_LISTS(state, data) {
      state.list = data;
    },
    SET_CURRENT_MEMBERS(state, data) {
      state.members = data
    },
    ADD_LIST (state, listToAdd) {
      state.list.push(listToAdd);
    },
    UPDATE_CLAIMED_ID(state, listAndUser) {
      let curList = state.list.find(l => l.listId == listAndUser.listId)
      console.log(listAndUser)
      console.log(curList)
      curList.claimedId = listAndUser.userId
      console.log(curList)
    },
    SET_ITEMS (state, items) {
      state.items = items
    }
  }
})
