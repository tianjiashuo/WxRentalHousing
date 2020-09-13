// pages/myhouse/myhouse.js
import renting from '../../models/Renting';
Page({

  /**
   * 页面的初始数据
   */
 
  data: {
    maskFlag:true,
    //公共表单
    wether_rent_sell:"-1",
    title:"",
    address:"",
    type:"",
    orientation:"",
    floor:"",
    is_elevator:"",
    area:"",
    price:"",
    //区别表单
    rentinfo:{
     is_pet:"",
     shortrst_lease:"",
     furniture:"",
     form:""
     },
     sellinfo:{
      property:"",
      is_renovation:""
     },
     selectArray: [{
      "id": "0",
      "text": "我要发布租房信息"
    }, {
      "id": "1",
      "text": "我要发布买房信息"
    }]
   },

   //获取发布类型
   wetherRentSellDate:function(e){
    this.setData({
     'wether_rent_sell':e.detail.id
    })
   },
   //提交数据分转
   dopost:function(e){
    console.log("form 发生了 submit",e.detail.value)
    if( this.data.wether_rent_sell==0){
     this.dopostRent(e);
    }else if(this.data.wether_rent_sell==1){
     this.dopostSell(e);
    }else{
    console.log("非法submit")
    }
   },
   //提交租房
   dopostRent:function(e){
   // console.log("form 发生了 submit",e.detail.value)
   let that = this;
     wx.request({
      url: 'http://47.94.170.167:8080/insertRentHouse',
      //url:'http://localhost:8080/insertRentHouse', 
      data:{
         'hostId':wx.getStorageSync('openId'),
         "shortestLease": e.detail.value.shortrst_lease,
         "area": e.detail.value.area,
         "price": e.detail.value.price,
         "floor": e.detail.value.floor,
         "address":e.detail.value.address,
         "title": e.detail.value.title,
         "type": e.detail.value.type,
         "orientation": e.detail.value.orientation,
         "furniture": e.detail.value.furniture,
         "isElevator": e.detail.value.is_elevator,
         "isPet": e.detail.value.is_pet,
         "isForm": e.detail.value.form,
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
   //提交卖房
   dopostSell:function(e){
    let that = this;
    wx.request({
       url: 'http://47.94.170.167:8080/insertSellHouse',
       //url:'http://localhost:8080/insertSellHouse',
      data:{
        'hostId':'99',
        "address":e.detail.value.address,
        "title":e.detail.value.title,
        "property":e.detail.value.property,
        "type":e.detail.value.type,
        "orientation":e.detail.value.orientation,
        "floor":e.detail.value.floor,
        "isRenovation":e.detail.value.is_renovation,
        "isElevator":e.detail.value.is_elevator,
        "area":e.detail.value.area,
        "furniture":e.detail.value.furniture,
        "price":e.detail.value.price
      },
     method:'POST',
     header: {
       'Content-Type': 'application/json'
     },
     success: function (res) {
       console.log(res.data);
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
        })
       },2000)
     }
    })
    
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

