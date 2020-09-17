// pages/sellde/sellde.js
Page({

  
  /**
   * 页面的初始数据
   */
  data: {
    id: 1,
    hostId:0,
    shortestLease:0,
    area: 0,
    price: 0,
    floor: 0,
    address: "",
    title: "",
    type: "",
    orientation: "",
    furniture: "",
    isElevator: false,
    isPet: false,
    isForm: false,
    isState: false,
    isCollect: false,
    show: false,
    isReport:false
  },
  

  // showMask: function() {//显示文本框
  //   this.setData({
  //     show: true
  //   })
  // },

  // sub: function() {//提交工作内容
  //   wx.showToast({
  //     title: '提交成功',
  //     duration: 1000
  //   })
    
  //   this.setData({
  //     show: false
  //   })
  // },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var hId = options.id
    let that = this;
    wx.request({
      url: 'http://47.94.170.167:8080/sellAllInfo/'+options.id,
      method:'GET',
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
         title:res.data.sellInfo.title,
         address:res.data.sellInfo.address,
         type:res.data.sellInfo.type,
         orientation:res.data.sellInfo.orientation,
         furniture:res.data.sellInfo.furniture,
         isElevator:res.data.sellInfo.isElevator,
         id:res.data.sellInfo.id,
         price:res.data.sellInfo.price,
         images:res.data.imageList,
         hostId:res.data.sellInfo.hostId
        })
        wx.setStorage({
          key:"1",
          data:res.data.sellInfo.id
        })
        wx.request({
          url: 'http://47.94.170.167:8080/userInfo/'+res.data.sellInfo.hostId,
          method:'GET',
          header: {
            'Content-Type': 'application/json'
          },
          success:function(res){
            console.log(res.data)
            that.setData({
              head:res.data.head,
              nickname:res.data.nickname,
              introduction:res.data.introduction,
              phone:res.data.phone,
              gender:res.data.gender
            })
          }
        })
      }
    })

    // wx.getStorage({
    //   key: '1',
    //   success: function(res) {
    //       console.log(res.data)
    //       this.hostId = res.data
    //   }
    // })

  },

  toCollect () {
    var bol = this.data.isCollect; // 获取状态
    this.setData({
    isCollect:!bol // 改变状态
    })
    wx.getStorage({
      key: '1',
      success: function(res) {
          console.log(res.data)
          if(!bol == true){
            wx.request({
              url: 'http://localhost:8080/addCollection',
              data:{
                "userId":1,
                "houseId":res.data,
                "houseType":1
              },
              method:"POST",
              header: {
                'Content-Type': 'application/json'
              },
              success:function(res){
                console.log(res.data)
              }
            })
          }
          else{
            wx.request({
              url: 'http://localhost:8080/cancelCollection/'+id,
              method:"DELETE",
              header: {
                'Content-Type': 'application/json'
              },
              success:function(res){
                console.log("删除成功"+res.data)
              }
            })
          }
      }
    })
    },

    goNewsDetail:function(event)
    {
     wx.navigateTo({
       url: '/pages/report/report'
     })
    },

    addreport(){
wx.getStorage({
  key: '1',
  success:function(res){
    var hostId = res.data
    wx.getStorage({
      key: 'report',
      success:function(res){
        console.log(res.data)
      }
    })
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

  }
})