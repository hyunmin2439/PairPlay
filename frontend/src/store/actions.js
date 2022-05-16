import store from "@/common/store";
import $axios from "axios";

export async function signupDuplicateEmail({ commit }, payload) {
  const url = "members/check/email";
  const body = payload;
  await $axios
    .post(url, body)
    .then((res) => {
      if (res.data.code == 200) {
        commit("SIGNUP_DUPLICATE_EMAIL", 1);
        alert("사용 가능한 이메일입니다.");
      } else {
        commit("SIGNUP_DUPLICATE_EMAIL", 0);
        alert("이미 존재하는 이메일입니다.");
      }
    })
    .catch(() => {
      commit("SIGNUP_DUPLICATE_EMAIL", 0);
      alert("이미 존재하는 이메일입니다.");
    });
}

export async function signupDuplicateNickname({ commit }, payload) {
  const url = "members/check/nickname";
  const body = payload;
  await $axios
    .post(url, body)
    .then((res) => {
      if (res.data.code == 200) {
        commit("SIGNUP_DUPLICATE_NICKNAME", 1);
        alert("사용 가능한 닉네임입니다.");
      } else {
        commit("SIGNUP_DUPLICATE_NICKNAME", 0);
        alert("이미 존재하는 닉네임입니다.");
      }
    })
    .catch(() => {
      commit("SIGNUP_DUPLICATE_NICKNAME", 0);
      alert("이미 존재하는 닉네임입니다.");
    });
}

export async function signup({ state }, payload) {
  const url = "members/signup";
  const body = payload;
  console.log(state);
  await $axios
    .post(url, body)
    .then((res) => {
      localStorage.setItem("jwt", res.data.accessToken);
    })
    .catch((err) => {
      console.log(err);
    });
}

// Header랑 Body 동시 송출 방법 *******
export async function signupSecond({ commit }, payload) {
  const url = "members/signup";
  const header = localStorage.getItem("jwt");
  const body = payload;
  await $axios
    .put(url, body, {
      headers: {
        Authorization: "Bearer " + header,
      },
    })
    .then((res) => {
      commit("USER_INFO", res.data);
      commit("LOGIN_STATUS", true);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function login({ commit }, payload) {
  const url = "members/signin";
  const body = payload;
  await $axios
    .post(url, body)
    .then((res) => {
      localStorage.setItem("jwt", res.data.accessToken);
      commit("USER_INFO", res.data);
      commit("LOGIN_STATUS", true);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function getUserInfo({ commit }, payload) {
  const memberId = payload.memberId;
  const jwt = payload.jwt;
  const url = `profiles/${memberId}`;
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("USER_INFO", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileOtherInfo({ commit }, payload) {
  const memberId = payload.memberId;
  const jwt = payload.jwt;
  const url = `profiles/${memberId}`;
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("OTHER_INFO", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileChangeInfo({ state, dispatch }, payload) {
  const body = payload;
  const memberId = state.userInfo.memberId;
  const url = `profiles`;
  const header = localStorage.getItem("jwt");
  await $axios
    .put(url, body, {
      headers: {
        Authorization: "Bearer " + header,
      },
    })
    .then(() => {
      dispatch("getOtherInfo", {
        memberId: memberId,
        jwt: header,
      });
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileChangeImage({ state }, payload) {
  const body = payload;
  console.log(state);
  // const memberId = state.userInfo.memberId;
  const url = "profiles/profileImage";
  const header = localStorage.getItem("jwt");
  await $axios
    .put(url, body, {
      headers: {
        Authorization: "Bearer " + header,
        "Content-Type": "multipart/form-data",
      },
    })
    .then((res) => {
      // dispatch("getOtherInfo", {
      //   memberId: memberId,
      //   jwt: header,
      // });
      console.log(res);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileUserSchedule({ commit }) {
  const url = `profiles/calendar`
  const header = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + header,
      },
    })
    .then((res) => {
      console.log(typeof(res.data.list[0].date[0]))
      commit("PROFILE_USER_SCHEDULE", res.data.list)
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileDateTodo({ commit }, payload) {
  const url = `profiles/calendar/activity`
  const jwt = localStorage.getItem("jwt")
  const body = payload
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_ACTIVITY_PER_DAY", res.data.calendarDetailActivityResList)
      commit("PROFILE_DATE_PER_DAY", body.date)
    })
    .catch((err) => {
      console.log(err)
  }) 
}

export async function profileMateListFrom({ commit }, payload) {
  const page = payload["body"]
  const size = payload["size"]
  const url = `profiles/mates/received?page=${page}&size=${size}`
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      commit("PROFILE_MATE_LIST_FROM", res.data)
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function profileMateListTo({ commit }, payload) {
  const page = payload["body"]
  const size = payload["size"]
  const url = `profiles/mates/send?page=${page}&size=${size}`
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt
      }
    })
    .then((res) => {
      commit("PROFILE_MATE_LIST_TO", res.data)
    })
    .catch((err) => {
      console.log(err)
  })
}

export async function mateArticleList({ commit }, payload) {
  const page = payload["page"];
  const size = payload["size"];
  const jwt = localStorage.getItem("jwt");
  const url = `mates?page=${page}&size=${size}`;
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then(async (res) => {
      console.log(res.data.list, "있음?");
      if (res.data.list.length == 0) {
        alert("해당결과가 없습니다.");
      } else {
        await commit("MATE_ARTICLE_LIST", res.data.list);
        await commit("MATE_ARTICLE_PAGE", page);
      }
      // await commit("MATE_ARTICLE_LIST", res.data.list);
      // await commit("MATE_ARTICLE_PAGE", page);
    })
    .catch((err) => {
      console.log(err);
    });
}
export async function change({ commit }, payload) {
  await commit("CHANGE", payload);
}

export async function getPlaceSearchInfo({ commit }, searchFiltersData) {
  const page = searchFiltersData.page;
  // console.log(page, "페이지");
  let body = searchFiltersData;
  // console.log(body, "바디");

  if (body.categoryList.length === 0) {
    // console.log("안되나");
    body.categoryList = [store.state.root.selectSportsCategory];
  } else {
    body = searchFiltersData;
  }
  const size = 20;
  const jwt = localStorage.getItem("jwt");
  const url = `places/search?page=${page}&size=${size}`;
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      console.log(res);
      commit("PLACE_SEARCH_INFO", res.data);
      commit("CHANGE_POSITION", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function selectSportsCategory({ commit }, categoryList) {
  const selectSportsCategory = Object.values(categoryList)[0];
  await commit("SELECT_SPORTS_CATEGORY", selectSportsCategory);
}

export async function addPlaceFilters({ commit }, data) {
  await commit("ADD_PLACE_FILTERS", data);
  await store.dispatch("root/getPlaceSearchInfo", data);
}

export async function getPlaceDetailInfo({ commit }, id) {
  // console.log(page, "페이지");
  // console.log(id, "id");
  const jwt = localStorage.getItem("jwt");
  const url = `places/${id}`;
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      console.log(res.data);
      commit("PLACE_DETAIL_INFO", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}
