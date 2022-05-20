import store from "@/common/store";
import $axios from "axios";
import Swal from "sweetalert2";

export async function signupDuplicateEmail({ commit }, payload) {
  const url = "members/check/email";
  const body = payload;
  await $axios
    .post(url, body)
    .then((res) => {
      if (res.data.code == 200) {
        commit("SIGNUP_DUPLICATE_EMAIL", 1);
        Swal.fire({
          icon: "success",
          title: "성공!",
          text: "사용 가능한 이메일입니다.",
        });
      } else {
        commit("SIGNUP_DUPLICATE_EMAIL", 0);
        Swal.fire({
          icon: "error",
          title: "실패...",
          text: "이미 존재하는 이메일입니다.",
        });
      }
    })
    .catch(() => {
      commit("SIGNUP_DUPLICATE_EMAIL", 0);
      Swal.fire({
        icon: "error",
        title: "실패...",
        text: "이미 존재하는 이메일입니다.",
      });
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
        Swal.fire({
          icon: "success",
          title: "성공!",
          text: "사용 가능한 닉네임입니다.",
        });
      } else {
        commit("SIGNUP_DUPLICATE_NICKNAME", 0);
        Swal.fire({
          icon: "error",
          title: "실패...",
          text: "이미 존재하는 닉네임입니다.",
        });
      }
    })
    .catch(() => {
      commit("SIGNUP_DUPLICATE_NICKNAME", 0);
      Swal.fire({
        icon: "error",
        title: "실패...",
        text: "이미 존재하는 닉네임입니다.",
      });
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
      if (res.data.code == 200) {
        localStorage.setItem("jwt", res.data.accessToken);
        commit("USER_INFO", res.data);
        commit("LOGIN_STATUS", true);
      } else {
        console.log(res);
      }
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

export async function loginResetPassword({ state }, payload) {
  const url = `members/password`;
  const body = payload;
  console.log(state);
  await $axios
    .post(url, body)
    .then(() => {
      Swal.fire({
        icon: "success",
        title: "성공!",
        text: "해당 이메일로 임시 비밀번호가 발급되었습니다.",
      });
    })
    .catch((err) => {
      console.log(err);
      Swal.fire({
        icon: "error",
        title: "실패...",
        text: "임시 비밀번호 발급이 실패했습니다.",
      });
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
      dispatch("profileOtherInfo", {
        memberId: memberId,
        jwt: header,
      });
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileChangeImage({ dispatch }, payload) {
  const formData = new FormData();
  formData.append("profileImage", payload.file);
  const memberId = payload.memberId;
  const url = "profiles/profileImage";
  const jwt = localStorage.getItem("jwt");
  const header = {
    headers: {
      Authorization: "Bearer " + jwt,
      "Content-Type": "multipart/form-data",
    },
  };
  await $axios
    .post(url, formData, header)
    .then((res) => {
      dispatch("profileOtherInfo", {
        memberId: memberId,
        jwt: jwt,
      });
      console.log(res);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileChangePassword({ state }, payload) {
  console.log(state);
  const url = `profiles`;
  const body = payload;
  const header = localStorage.getItem("jwt");
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + header,
      },
    })
    .then(() => {
      Swal.fire({
        icon: "success",
        title: "성공!",
        text: "비밀번호 변경에 성공했습니다.",
      });
    })
    .catch(() => {
      Swal.fire({
        icon: "error",
        title: "실패...",
        text: "비밀번호 변경에 실패했습니다.",
      });
    });
}

export async function profileUserSchedule({ commit }, payload) {
  const memberId = payload;
  const url = `profiles/calendar/${memberId}`;
  const header = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + header,
      },
    })
    .then((res) => {
      commit("PROFILE_USER_SCHEDULE", res.data.list);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileDateTodo({ commit }, payload) {
  const url = `profiles/calendar/activity`;
  const jwt = localStorage.getItem("jwt");
  const body = payload;
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_ACTIVITY_PER_DAY", res.data.calendarDetailActivityResList);
      commit("PROFILE_DATE_PER_DAY", body.date);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileMateListFrom({ commit }, payload) {
  const page = payload["page"];
  const size = payload["size"];
  const url = `profiles/mates/received?page=${page}&size=${size}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_MATE_LIST_FROM", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileMateFromAccept({ dispatch }, payload) {
  const url = "profiles/mates/accept";
  const body = { id: payload["id"] };
  const page = payload["page"];
  const jwt = localStorage.getItem("jwt");
  await $axios
    .put(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then(async () => {
      await dispatch("profileMateListFrom", {
        page: page,
        size: 3,
      });
      Swal.fire({
        icon: "success",
        title: "성공!",
        text: "해당 메이트 신청을 수락했습니다.",
      });
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileMateFromReject({ dispatch }, payload) {
  const id = payload["id"];
  const url = `profiles/mates/reject/${id}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .delete(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then(async () => {
      await dispatch("profileMateListFrom", {
        page: payload["page"],
        size: 3,
      });
      Swal.fire({
        icon: "success",
        title: "성공!",
        text: "해당 메이트 신청을 거절했습니다.",
      });
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileMateListTo({ commit }, payload) {
  const page = payload["page"];
  const size = payload["size"];
  const url = `profiles/mates/send?page=${page}&size=${size}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_MATE_LIST_TO", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileReservationList({ commit }, payload) {
  const page = payload;
  const url = `profiles/places/${page}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileReservationListMore({ commit }, payload) {
  const page = payload;
  const url = `profiles/places/${page}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST_MORE", { dataList: res.data, page: page });
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileReservationDoingList({ commit }, payload) {
  const page = payload;
  const url = `profiles/places/reservation/${page}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileReservationDoingListMore({ commit }, payload) {
  const page = payload;
  const url = `profiles/places/reservation/${page}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST_MORE", { dataList: res.data, page: page });
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileReservationDoneList({ commit }, payload) {
  const page = payload;
  const url = `profiles/places/used/${page}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileReservationDoneListMore({ commit }, payload) {
  const page = payload;
  const url = `profiles/places/used/${page}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_RESERVATION_LIST_MORE", { dataList: res.data, page: page });
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileCancelPlaceReservation({ dispatch }, payload) {
  const url = `places/reservation`;
  const body = payload;
  console.log(body);
  console.log(typeof body["reservationId"]);
  const jwt = localStorage.getItem("jwt");
  await $axios
    .delete(
      url,
      {
        data: {
          reservationId: body["reservationId"],
        },
      },
      {
        headers: {
          Authorization: "Bearer " + jwt,
        },
      }
    )
    .then(() => {
      dispatch("profileReservationList");
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileMateToCancle({ dispatch }, payload) {
  const id = payload["id"];
  const url = `profiles/mates/cancel/${id}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .delete(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then(async () => {
      await dispatch("profileMateListTo", {
        page: payload["page"],
        size: 3,
      });
      Swal.fire({
        icon: "success",
        title: "성공!",
        text: "해당 메이트 신청이 취소되었습니다.",
      });
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileLikePlace({ commit }, payload) {
  const page = payload;
  const url = `profiles/places/like/${page}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_LIKE_PLACE", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileLikePlaceCancle({ dispatch }, payload) {
  const placeId = payload["id"];
  const page = payload["page"];
  const url = `places/like/${placeId}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .put(
      url,
      {},
      {
        headers: {
          Authorization: "Bearer " + jwt,
        },
      }
    )
    .then(() => {
      dispatch("profileLikePlace", page);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileLikeMate({ commit }, payload) {
  const page = payload;
  const url = `profiles/mates/like?page=${page}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("PROFILE_LIKE_MATE", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileLikeMateCancle({ dispatch }, payload) {
  const activityId = payload["id"];
  const page = payload["page"];
  const url = `mates/like/${activityId}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .put(
      url,
      {},
      {
        headers: {
          Authorization: "Bearer " + jwt,
        },
      }
    )
    .then(() => {
      dispatch("profileLikeMate", page);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function profileCreateReview({ dispatch }, payload) {
  const formData = payload;
  const url = `places/review`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .post(url, formData, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then(async () => {
      await dispatch("profileReservationList", 0);
      Swal.fire({
        icon: "success",
        title: "성공!",
        text: "리뷰 등록에 성공하셨습니다.",
      });
    })
    .catch((err) => {
      console.log(err);
      Swal.fire({
        icon: "error",
        title: "실패...",
        text: "리뷰 등록에 실패하셨습니다.",
      });
    });
}

export async function reviewDetailInfo({ commit }, payload) {
  const reservationId = payload.reservationId;
  const url = `places/review/${reservationId}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(
      url,
      {},
      {
        headers: {
          Authorization: jwt,
        },
      }
    )
    .then((res) => {
      console.log(res);
      commit("REVIEW_DETAIL_INFO", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function mateCreate({ state }, payload) {
  console.log(state);
  const body = payload;
  const url = `mates/mate`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      console.log(res);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function mateArticleList({ commit }, payload) {
  const page = payload["page"];
  const jwt = localStorage.getItem("jwt");
  const url = `mates?page=${page}`;
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then(async (res) => {
      if (res.data.list.length == 0) {
        Swal.fire({
          icon: "error",
          title: "실패...",
          text: "해당결과가 없습니다.",
        });
      } else {
        await commit("MATE_ARTICLE_FILTER", {
          categoryId: 0,
          gungu: "",
          search: "",
          sido: "",
        });
        await commit("MATE_ARTICLE_LIST", res.data);
      }
      // await commit("MATE_ARTICLE_LIST", res.data.list);
      // await commit("MATE_ARTICLE_PAGE", page);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function mateDetailInfo({ commit }, payload) {
  const activityId = payload.activityId;
  const url = `mates/${activityId}`;
  const jwt = localStorage.getItem("jwt");
  await $axios
    .get(
      url,
      {},
      {
        headers: {
          Authorization: "Bearer " + jwt,
        },
      }
    )
    .then((res) => {
      console.log(res);
      commit("MATE_DETAIL_INFO", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function mateFilterChange({ commit }, payload) {
  const jwt = localStorage.getItem("jwt");
  const body = {
    categoryId: payload.categoryId,
    gungu: payload.gungu,
    search: payload.search,
    sido: payload.sido,
  };
  console.log(body);
  const page = payload.page;
  const url = `mates?page=${page}`;
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      commit("MATE_ARTICLE_FILTER", body);
      commit("MATE_ARTICLE_LIST", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function mateApplyFor({ state }, payload) {
  const body = payload;
  const url = `mates/register`;
  const jwt = localStorage.getItem("jwt");
  console.log(state);
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then(() => {
      Swal.fire({
        icon: "success",
        title: "성공!",
        text: "메이트 신청에 성공했습니다.",
      });
    })
    .catch(() => {
      Swal.fire({
        icon: "error",
        title: "실패...",
        text: "메이트 신청에 실패했습니다.",
      });
    });
}

export async function mateLikeChange({ state }, payload) {
  console.log(state);
  const activityId = payload.activityId;
  const url = `mates/like/${activityId}`;
  const jwt = localStorage.getItem("jwt");
  $axios
    .put(
      url,
      {},
      {
        headers: {
          Authorization: "Bearer " + jwt,
        },
      }
    )
    .then((res) => {
      console.log(res);
    })
    .catch((err) => {
      console.log(err);
    });
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
      // console.log(res)
      if (res.data.placeList.length >= 1) {
        commit("PLACE_SEARCH_INFO", res.data);
        commit("CHANGE_POSITION", res.data);
      } else {
        Swal.fire({
          icon: "error",
          title: "실패...",
          text: "데이터가없습니다.",
        });
      }
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
  const jwt = localStorage.getItem("jwt");
  const url = `places/${id}`;
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      // console.log(res.data);
      commit("PLACE_DETAIL_INFO", res.data);
    })
    .catch((err) => {
      console.log(err);
    });
}
export async function getPlaceRecommend({ commit }) {
  const jwt = localStorage.getItem("jwt");
  const url = "places/popular";
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      // console.log(res.data.placeList);
      commit("PLACE_RECOMMEND", res.data.placeList.splice(0, 5));
    })
    .catch((err) => {
      console.log(err);
    });
}
export async function getPlaceRecent({ commit }) {
  const jwt = localStorage.getItem("jwt");
  const url = "places/recent";
  await $axios
    .get(url, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      // console.log(res.data.placeList);
      commit("PLACE_RECENT", res.data.placeList.splice(0, 5));
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function checkReservation({ commit }, payload) {
  const jwt = localStorage.getItem("jwt");
  const url = "places/reservation/check";
  // let body = { placeId: payload.placeId, reservationDt: payload.reservationDt };
  const body = payload;
  // console.log(body, "굿바디");
  await $axios
    .post(url, body, {
      headers: {
        Authorization: "Bearer " + jwt,
      },
    })
    .then((res) => {
      // console.log(res, "나오나");
      commit("CHECK_RESERVATION", res.data.times);
    })
    .catch((err) => {
      console.log(err);
    });
}

export async function showMapMarker({ commit }, card) {
  commit("SHOW_MAP_MARKER", card);
  // console.log(card, "굿바디");
}
