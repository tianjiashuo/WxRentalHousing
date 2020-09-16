// pages/my/my.js
let app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    hasUserInfo:false, 
    userName: "",
    userImgUrl: "",
    gender:"",
    phone:"",
    introduction:"",
    IDnumber:"",
  },

  goEditMyInfo:function()
  {
   wx.navigateTo({
     url: '/pages/editmyinfo/editmyinfo'
   })
   }, 
 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var openId=wx.getStorageSync('openId');
    console.log("openId -----" +openId);
      if(typeof(openId) != 'undefined'){
      wx.request({
        url:'http://47.94.170.167:8080/userInfo/'+openId,
        method:'GET',
        header: {
          'Content-Type': 'application/json'
        },
        success: function (res) {
          console.log(res.data)
          console.log(11111)
          that.setData({
            gender:res.data.gender?"男":"女",
            phone:res.data.phone,
            introduction:res.data.introduction,
            IDnumber:res.data.idNumber,
          })
        }
      })
    }
   
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
    this.setData({
      hasUserInfo: app.globalData.hasUserInfo,
      userName: app.globalData.userInfo.nickName,
      userImgUrl: app.globalData.userInfo.head,
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },



  getUserInfo: function (e) {
    // var that = this;
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
            
             wx.request({
               url: 'http://47.94.170.167:8080/user/login',
               method: 'POST',
               data: jsonData,
               header: {
                 'content-type': 'application/json'
               },
               success(res) {
                 if (res.data.openid != "" || res.data.openid!=null){
                   // 登录成功
                     wx.setStorageSync("openId", res.data.userInfo.openId);//将用户id保存到缓存中
                     app.globalData.hasUserInfo = true;
                     app.globalData.userInfo.nickName = res.data.userInfo.nickName;
                     app.globalData.userInfo.head =  res.data.userInfo.avatarUrl;
                     app.globalData.userInfo.gender = res.data.userInfo.gender;
               
                   console.log("res userinfo ", res);
                   console.log(app.globalData.hasUserInfo);
                   // console.log("session_keymypage",res.data.session_key);
                 }else{
                   // 登录失败
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
  }
  
})
