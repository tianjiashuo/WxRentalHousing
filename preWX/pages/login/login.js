//index.js
//获取应用实例
const app = getApp()

Page({
  data: {

  },
  onLoad: function () {

  },
  getUserInfo: function (e) {
     // 登录
     wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        console.log(res);
        if (res.code) {
          wx.getUserInfo({
            success:function(useres){
              console.log(useres)
              var jsonData={
                code:res.code,
                encryptedData: useres.encryptedData,
                iv:useres.iv
              };
              console.log("mypage jsonData",jsonData);
              wx.request({
                url: 'http://localhost:8080/user/login',
                method: 'POST',
                data: jsonData,
                header: {
                  'content-type': 'application/json'
                },
                success(res) {
                  if (res.data.openid != "" || res.data.openid!=null){
                    // 登录成功
                    //  wx.setStorageSync("openid", res.data.openid);//将用户id保存到缓存中
                    //  wx.setStorageSync("session_key", res.data.session_key);//将session_key保存到缓存中
                    console.log("res mypage", res);
                    // console.log("session_keymypage",res.data.session_key);
                  }else{
                    // 登录失败
                    // TODO 跳转到错误页面，要求用户重试
                    return false;
                  }
                }
              })
            }
          })
        } else {
          console.log('获取用户登录态失败！' + res.errMsg)
        }
      }
    });
    console.log(e);
    app.globalData.hasUserInfo = true;
    app.globalData.userInfo.nickName = e.detail.userInfo.nickName;
    app.globalData.userInfo.head = e.detail.userInfo.avatarUrl;
    wx.navigateBack({
      url: '../mypage/mypage'
    })
  }
})