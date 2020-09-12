//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    
    // // 登录
    // wx.login({
    //   success: res => {
    //     // 发送 res.code 到后台换取 openId, sessionKey, unionId
    //     if (res.code) {
    //       wx.request({
    //         url: 'http://localhost:8080/user/login',
    //         method: 'POST',
    //         data: {
    //           code: res.code
    //         },
    //         header: {
    //           'content-type': 'application/x-www-form-urlencoded'
    //         },
    //         success(res) {
    //           if (res.data.openid != "" || res.data.openid!=null){
    //             // 登录成功
    //              wx.setStorageSync("openid", res.data.openid);//将用户id保存到缓存中
    //              wx.setStorageSync("session_key", res.data.session_key);//将session_key保存到缓存中
    //             console.log("openid", res.data.openid);
    //             console.log("session_key",res.data.session_key);
    //           }else{
    //             // 登录失败
    //             // TODO 跳转到错误页面，要求用户重试
    //             return false;
    //           }
    //         }
    //       })
    //     } else {
    //       console.log('获取用户登录态失败！' + res.errMsg)
    //     }
    //   }
    // })
   
    // // 获取用户信息
    // wx.getSetting({
    //   success: res => {
    //     if (res.authSetting['scope.userInfo']) {
    //       // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
    //       wx.getUserInfo({
    //         success: res => {
    //           // 可以将 res 发送给后台解码出 unionId
    //           console.log(res.userInfo)
    //           this.globalData.userInfo.nickName = res.userInfo.nickName;
    //           this.globalData.userInfo.head=res.userInfo.avatarUrl;
    //           this.globalData.userInfo.gender=res.userInfo.gender;
    //           // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
    //           // 所以此处加入 callback 以防止这种情况
    //           if (this.userInfoReadyCallback) {
    //             this.userInfoReadyCallback(res)
    //           }
    //         }
    //       })
    //     }
    //   }
    // })
    
  },
  globalData: {
    hasUserInfo:false,
    userInfo:{
      head:'',
      nickName:'',
      phone:'',
      gender:'',
    },
   
    
  },

})