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

export function GET_USER_SCHEDULE(state, data) {
  state.userSchedule = []
  for (var i in data) {
    var sub = {}
    sub['date'] = String(data[i].date[0]) + '-' + String(data[i].date[1]) + '-' + String(data[i].date[2])
    sub['count'] = data[i].count
    state.userSchedule.push(sub)
  }
}

export function ACTIVITY_PER_DAY(state, data) {
  state.activityPerDay = data
}

export function DATE_PER_DAY(state, data) {
  state.datePerDay = data
}

export async function MATE_ARTICLE_LIST(state, data) {
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
    sub['timeDiff'] = Math.floor(diff / (1000*60*60*24))
    // console.log(new Date(k.getTimezoneOffset() * 60000).toISOString())
      // String(data[i].date[0]) + '-' + String(data[i].date[1]) + '-' + String(data[i].date[2])
    state.mateArticleList.push(sub)
  }
}

export function Place_Search_Info(state, data) {
  state.placeSearchInfo = data;
}

export function SELECT_SPORTS_CATEGORY(state, data) {
  state.selectSportsCategory = data;
}

export async function CHANGE(state, data) {
  state.changeList = {}
  state.changeList = data
}