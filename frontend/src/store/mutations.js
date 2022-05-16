// import state from "./state";

export function SIGNUP_DUPLICATE_EMAIL(state, data) {
  state.emailDuplicate = data;
}

export function SIGNUP_DUPLICATE_NICKNAME(state, data) {
  state.nicknameDuplicate = data;
}

export function LOGIN_STATUS(state, data) {
  state.loginStatus = data;
}

export function USER_INFO(state, data) {
  state.userInfo["address"] = data.address;
  state.userInfo["birthDt"] = data.birthDt;
  state.userInfo["description"] = data.description;
  state.userInfo["email"] = data.email;
  if (data.gender == 0) {
    state.userInfo["gender"] = "남성";
  } else {
    state.userInfo["gender"] = "여성";
  }
  state.userInfo["memberId"] = data.memberId;
  state.userInfo["name"] = data.name;
  state.userInfo["nickname"] = data.nickname;
  state.userInfo["phone"] = data.phone;
  state.userInfo["profileImage"] = data.profileImage;
}

export function OTHER_INFO(state, data) {
  state.otherInfo = []
  state.otherInfo["address"] = data.address;
  state.otherInfo["birthDt"] = data.birthDt;
  state.otherInfo["description"] = data.description;
  state.otherInfo["email"] = data.email;
  if (data.gender == 0) {
    state.otherInfo["gender"] = "남성";
  } else {
    state.otherInfo["gender"] = "여성";
  }
  state.otherInfo["memberId"] = data.memberId;
  state.otherInfo["name"] = data.name;
  state.otherInfo["nickname"] = data.nickname;
  state.otherInfo["phone"] = data.phone;
  state.otherInfo["profileImage"] = data.profileImage;
}

export function PROFILE_USER_SCHEDULE(state, data) {
  state.profileUserSchedule = []
  for (var i in data) {
    var sub = {}
    sub['date'] = String(data[i].date[0]) + '-' + String(data[i].date[1]) + '-' + String(data[i].date[2])
    sub['count'] = data[i].count
    state.profileUserSchedule.push(sub)
  }
}

export function PROFILE_ACTIVITY_PER_DAY(state, data) {
  state.profileActivityPerDay = data
}

export function PROFILE_DATE_PER_DAY(state, data) {
  state.profileDatePerDay = data
}

export function PROFILE_MATE_LIST_FROM(state, data) {
  state.profileMateListFromTotalPage = 0
  state.profileMateListFromTotalPage = data.totalPages
  state.profileMateListFrom = []
  state.profileMateListFrom = data.list
}

export function PROFILE_MATE_LIST_TO(state, data) {
  state.profileMateListFromTotalPage = 0
  state.profileMateListToTotalPage = data.totalPages
  state.profileMateListTo = []
  state.profileMateListTo = data.list
}

export function MATE_ARTICLE_PAGE(state, data) {
  state.mateArticlePage = 0
  state.mateArticlePage = data + 1
}

export async function MATE_ARTICLE_LIST(state, data) {
  state.mateArticleListTotalElements = 0
  state.mateArticleListTotalElements = data.totalElements
  state.mateArticleListTotalPage = 0
  state.mateArticleListTotalPage = data.totalPages
  state.mateArticleList = [];
  for (var i in data.content) {
    var sub = {}
    sub['activityId'] = data.content[i]['activityId']
    sub['categoryId'] = data.content[i]['categoryId']
    sub['description'] = data.content[i]['description']
    sub['location'] = data.content[i]['location']
    sub['title'] = data.content[i]['title']
    var startDate = new Date(data.content[i]['createdDate'][0], data.content[i]['createdDate'][1]-1, data.content[i]['createdDate'][2])
    var today = new Date
    var diff = today.getTime() - startDate.getTime()
    sub['createdDate'] = String(data.content[i]['createdDate'][0]) + '-' + String(data.content[i]['createdDate'][1]) + '-' + String(data.content[i]['createdDate'][2])
    sub['timeDiff'] = Math.floor(diff / (1000*60*60*24))
    // console.log(new Date(k.getTimezoneOffset() * 60000).toISOString())
    // String(data[i].date[0]) + '-' + String(data[i].date[1]) + '-' + String(data[i].date[2])
    state.mateArticleList.push(sub);
  }
}

export async function PLACE_SEARCH_INFO(state, data) {
  state.placeSearchInfo = "";
  state.placeSearchInfo = data;
}

export async function SELECT_SPORTS_CATEGORY(state, data) {
  state.selectSportsCategory = "";
  state.selectSportsCategory = data;
}

export async function SUBMIT_LIKE(state, data) {
  state.placeLike = data;
}
export async function CHANGE(state, data) {
  state.changeList = {};
  state.changeList = data;
}
export async function ADD_PLACE_FILTERS(state, data) {
  state.addPlaceFilters = {};
  state.addPlaceFilters = data;
}

export async function PLACE_DETAIL_INFO(state, data) {
  state.placeDetailInfo = data.placeDetail;
}
export async function CHANGE_POSITION(state, data) {
  state.mapPosition = [];
  let longitude;
  let latitude;
  console.log(data);
  const markers = [];
  for (let i = 0; i < data.placeList.length; i++) {
    longitude = data.placeList[i].longitude;
    latitude = data.placeList[i].latitude;
    // console.log(tempLat);
    let tempList = { longitude, latitude };
    markers.push(tempList);
  }
  state.mapPosition = markers;
}
