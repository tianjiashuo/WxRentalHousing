// pages/renthouse/renthouse.js
Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    auditNum:0,
    current:0,
    audits:[],
  },
 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    // var openId=wx.getStorageSync('openId');
    // if(typeof(openId) != 'undefined'){
      wx.request({
        // url:'http://47.94.170.167:8080/getCollection/'+openId,
        url:'http://localhost:8080/getAuditByHostId/3',
        header: { "content-type": "application/json" },
        method: 'POST',
        dataType: 'json',
        responseType: 'text',
        success: function (res) {
          console.log("audits"+res.data)
          console.log(res)
          for(var i=0;i<res.data.length;i++){
            res.data[i].user.gender=res.data[i].user.gender?"男":"女";
          }
          that.setData({
            audits:res.data,
            auditNum:res.data.length
            // collections: res.data,
          })
          console.log(res.data.length);
          console.log(res.data[0].user);
        },
        fail: function (res) {
          console.log('fail')
        },
      })
    // } 
  },

  goNewsDetail:function(event)
  {
   wx.navigateTo({
     url: '/pages/detials/detials?id=' + event.currentTarget.dataset.newsid
   })
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
    var that = this;
    var openId=wx.getStorageSync('openId');
    if(typeof(openId) != 'undefined'){
      wx.request({
        url:'http://47.94.170.167:8080/getCollection/'+openId,
        header: { "content-type": "application/json" },
        method: 'POST',
        dataType: 'json',
        responseType: 'text',
        success: function (res) {
          console.log("collections"+res.data)
          console.log(res.data.length)
          that.setData({
            collections: res.data,
            collectionNum: res.data.length,
          })
        },
        fail: function (res) {
          console.log('fail')
        },
      })
    } 
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
  }
  ,

  noTouchMove: function() {
  },
  
})