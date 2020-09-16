// pages/myhouse/myhouse.js
Page({

  /**
   * 页面的初始数据
   */
 
  data: {
    //公共表单
    introduction:"",
    IDnumber:"",
   },


   //提交数据
   dopostInfo:function(e){
   // console.log("form 发生了 submit",e.detail.value)
   let that = this;
     wx.request({
      url: 'http://47.94.170.167:8080/editUserInfo',
      //url:'http://localhost:8080/editUserInfo', 
      data:{
         "id":wx.getStorageSync('openId'),
         "introduction": e.detail.value.introduction,
         "idNumber": e.detail.value.IDnumber,
       },
      method:'POST',
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        console.log(e.detail.value)
        that.setData({
          'maskFlag':false
        })
        wx.showToast({
          title: '修改成功',
          icon: 'success',
          duration: 2000
        }),
        setTimeout(function(){
          wx.hideToast();
          wx.navigateBack({
            delta: 1,
          });
         },2000)  
      }
     })
   },

   goBack:function()
   {
    wx.navigateBack({ delta:1 })
   },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var openId=wx.getStorageSync('openId');
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
            nickname:res.data.nickname,
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

})

