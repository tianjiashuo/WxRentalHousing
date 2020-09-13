//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
  
  },
  onLoad: function () {
   
  },
  getPhoneNumber: function(e) { 
 
    var jsonData={
      encryptedData:e.detail.encryptedData,
      iv:e.detail.iv,
      openId:wx.getStorageSync('openId')
    };
    wx.request({
      url: 'http://localhost:8080/user/getPhone',
      method: 'POST',
      data:jsonData,
      header: {
        'content-type': 'application/json'
      },
      success(res){
        console.log("res phone",res.data);
        app.globalData.userInfo.phone =res.data.phoneNumber;
      }
    })
    wx.navigateBack({
      url: '../mypage/mypage'
    })
  } 
})