// pages/myhouse/myhouse.js
import renting from '../../models/Renting';
Page({

  /**
   * 页面的初始数据
   */
 
  data: {
    //公共表单
    title:"",
    address:"",
    type:"",
    orientation:"",
    floor:"",
    is_elevator:"",
    area:"",
    price:""
   },

   //获取发布类型
   wetherRentSellDate:function(e){
    this.setData({
     'wether_rent_sell':e.detail.id
    })
   },

   //提交数据
   dopostInfo:function(e){
   // console.log("form 发生了 submit",e.detail.value)
   let that = this;
     wx.request({
      url: 'http://47.94.170.167:8080/insertRentHouse',
      //url:'http://localhost:8080/insertRentHouse', 
      data:{
         'id':wx.getStorageSync('openId'),
         "introduction": e.detail.value.shortrst_lease,
         "IDnumber": e.detail.value.area,
       },
      method:'POST',
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          'maskFlag':false
        })
        wx.showToast({
          title: '发布成功',
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

