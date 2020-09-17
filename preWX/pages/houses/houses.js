// pages/renthouse/renthouse.js
Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    current1:0,
    current2:0,
    rent:[],
    rentNum:0,
    sell:[],
    sellNum:0,
  },
 
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var openId=wx.getStorageSync('openId');
    if(typeof(openId) != 'undefined'){
      wx.request({
        // url:'http://localhost:8080/getUserHouse/3',
        url:'http://47.94.170.167:8080/getUserHouse/'+openId,
        header: { "content-type": "application/json" },
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: function (res) {
          console.log("rent"+res.data.rent.length)
          console.log("sell"+res.data.sell.length)
          that.setData({
            rent: res.data.rent,
            sell:res.data.sell,
            rentNum:res.data.rent.length,
            sellNum:res.data.sell.length,
          })
        },
        fail: function (res) {
          console.log(res)
        },
      })
    } 
  },

  goNewsDetail:function(event)
  {
   wx.navigateTo({
     url: '/pages/detials/detials?id=' + event.currentTarget.dataset.newsid
   })
  },

  btnTapRent:function(e){
    wx.showModal({
      title: '⚠️确认要删除该房源吗？⚠️',
      content: '',
      success: function (res) {
        if (res.confirm) {  
          console.log('点击确认')
          console.log("id=" + e.currentTarget.id);
          wx.request({
            // url: 'http://localhost:8080/rent/delete',
            url:'http://47.94.170.167:8080/rent/delete',
            header: { "content-type": "application/json" },
            method: 'POST',
            dataType: 'json',
            data:{
              "id":e.currentTarget.id
            },
            responseType: 'text',
            success: function (res) {
              console.log(res);
            },
            fail: function (res) {
              console.log(res)
            },
          })
          wx.request({
            url: 'http://localhost:8080/collectionChanged',
            header: { "content-type": "application/json" },
            method: 'POST',
            dataType: 'json',
            data:{
              "houseId":e.currentTarget.id,
              "houseType":"0"
            },
            responseType: 'text',
            success: function (res) {
              console.log(res);
            },
            fail: function (res) {
              console.log(res)
            }
          })
        } else {   
          console.log('点击取消')
        }
      }
    })
  },

  btnTapSell:function(e){
    wx.showModal({
      title: '⚠️确认要删除该房源吗？⚠️',
      content: '',
      success: function (res) {
        if (res.confirm) {  
          console.log('点击确认')
          console.log("id=" + e.currentTarget.id);
          wx.request({
            // url: 'http://localhost:8080/sell/delete',
            url:'http://47.94.170.167:8080/sell/delete',
            header: { "content-type": "application/json" },
            method: 'POST',
            dataType: 'json',
            data:{
              "id":e.currentTarget.id
            },
            responseType: 'text',
            success: function (res) {
              console.log(res);

            },
            fail: function (res) {
              console.log(res)
            },
          })
          wx.request({
            url: 'http://localhost:8080/collectionChanged',
            header: { "content-type": "application/json" },
            method: 'POST',
            dataType: 'json',
            data:{
              "houseId":e.currentTarget.id,
              "houseType":"1"
            },
            responseType: 'text',
            success: function (res) {
              console.log(res);
            },
            fail: function (res) {
              console.log(res)
            }
          })
        } else {   
          console.log('点击取消')
        }
      }
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
    var that = this;
    var openId=wx.getStorageSync('openId');
    if(typeof(openId) != 'undefined'){
      wx.request({
        // url:'http://localhost:8080/getUserHouse/3',
        url:'http://47.94.170.167:8080/getUserHouse/'+openId,
        header: { "content-type": "application/json" },
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: function (res) {
          console.log("rent"+res.data.rent.length)
          console.log("sell"+res.data.sell.length)
          that.setData({
            rent: res.data.rent,
            sell:res.data.sell,
            rentNum:res.data.rent.length,
            sellNum:res.data.sell.length,
          })
        },
        fail: function (res) {
          console.log(res)
        },
      })
      }
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
        // url:'http://localhost:8080/getUserHouse/3',
        url:'http://47.94.170.167:8080/getUserHouse/'+openId,
        header: { "content-type": "application/json" },
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: function (res) {
          console.log("rent"+res.data.rent)
          console.log("sell"+res.data.sell)
          that.setData({
            rent: res.data.rent,
            sell:res.data.sell,
          })
        },
        fail: function (res) {
          console.log(res)
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