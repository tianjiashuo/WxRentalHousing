//index.js
//获取应用实例
const app = getApp()

Page({
  data: {

  },
  onLoad: function () {

  },
  getUserInfo: function (e) {
    console.log(e);
    app.globalData.hasUserInfo = true;
    app.globalData.userName = e.detail.userInfo.nickName;
    app.globalData.userImgUrl = e.detail.userInfo.avatarUrl;
    wx.navigateBack({
      url: '../my/my'
    })
  }
})