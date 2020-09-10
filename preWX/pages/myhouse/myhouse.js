// pages/myhouse/myhouse.js
import renting from '../../models/Renting';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    rentinfo:{
      title:"",
      addr:"",
      property:"",
      type:"",
      orientation:"",
      floor:"",
      is_renovation:"",
      is_elevator:"",
      area:"",
      prive:"",
      state:""
     },
     selectArray: [{
      "id": "0",
      "text": "我要租房"
  }, {
      "id": "1",
      "text": "我要买房"
  }]

  },
  // close: function () {
  //   this.setData({
  //       showActionsheet: false
  //   })
  // },
  // btnClick(e) {
  //   console.log(showActionsheet)
  //   this.close()
  // },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
  getDate:function(e){
    console.log(e.detail)
  },

  upfile(){
    // wx.chooseImage({
    //   sizeType: ['original', 'compressed'],  //可选择原图或压缩后的图片
    //   sourceType: ['album', 'camera'], //可选择性开放访问相册、相机
    //   success: res => {
    //     const images = this.data.images.concat(res.tempFilePaths)
    //     // 限制最多只能留下3张照片
    //     this.data.images = images.length <= 3 ? images : images.slice(0, 3) 
    //     $digest(this)
    //   }
   // })

  }
})

