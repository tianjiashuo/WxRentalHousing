// pages/myhouse/myhouse.js
const qiniuUploader = require("../../utils/qiniuUploader");
//初始化七牛云
function initQiniu() {
  var options = {
      region: 'NCN',
      uptoken: wx.getStorageSync("upLoadToken"),
      // bucket 外链域名，下载资源时用到。如果设置，会在 success callback 的 res 参数加上可以直接使用的 fileURL 字段。否则需要自己拼接
      domain: 'http://qgqy1ci6z.hb-bkt.clouddn.com',
      shouldUseQiniuFileName: false
  };
  console.log("qiniu执行了 " + options.uptoken)
  // 将七牛云相关配置初始化进本sdk
  qiniuUploader.init(options);
}
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
    }],
    imgbox:[],
    urls:[],
    cancelTask: function () { }
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
       //上传图片逻辑
    if (!this.data.imgbox.length) {
      wx.showToast({
        icon:"none",
        title: '请至少添加一张图片'
        })
    } else {//有图片的提交
        //图片提交效果
        wx.showLoading({
          title: '上传中',
        })
        that.setData({
          'maskFlag':false
        })
        //上传图片到云存储
        let promiseArr = [];
        var urls = that.data.urls;
        for (let i = 0; i < that.data.imgbox.length; i++) {
          var p = new Promise((reslove, reject) => {
              let item = that.data.imgbox[i];
              console.log("准备上传111111111111"+item);
              qiniuUploader.upload(item, (res) => {
                //获得url
                console.log('file url is: ' + res.fileURL);
                if (urls.length == 0) {
                  urls=new Array(res.fileURL);
                } else{
                  urls = urls.concat(res.fileURL);
                }
                reslove();
            }, (error) => {
              reject();
              console.error('error: ' + JSON.stringify(error));
            }, null,
            (progress) => {
                console.log('上传进度', progress.progress);
            }, cancelTask => that.setData({ cancelTask }));
          })
          promiseArr.push(p);
        };
        Promise.all(promiseArr).then(res => {//等数组都做完后做then方法
          console.log("图片上传完成后再执行后台数据库交互")
          that.setData({
            urls:urls
          })
          console.log("我希望只执行一次好吗？",that.data.urls);
          wx.request({
            url: 'http://47.94.170.167:8080/insertRentHouse',
            //url:'http://localhost:8080/insertRentHouse',
            data:{
            'host_id':wx.getStorageSync('openId'),
            "shortest_lease": e.detail.value.shortest_lease,
            "area": e.detail.value.area,
            "price": e.detail.value.price,
            "floor": e.detail.value.floor,
            "address":e.detail.value.address,
            "title": e.detail.value.title,
            "type": e.detail.value.type,
            "orientation": e.detail.value.orientation,
            "furniture": e.detail.value.furniture,
            "is_elevator": e.detail.value.is_elevator,
            "is_pet": e.detail.value.is_pet,
            "form": e.detail.value.form,
            "images":that.data.urls
            },
            method:'POST',
            header: {
            'Content-Type': 'application/json'
            },
            success: res =>{
              console.log("数据库成功加入rent id为-> "+res.data);
                that.setData({
                imgbox:[]
              })
              wx.hideLoading({
                success: (res) => {
                  console.log("提交结束");
                  wx.showToast({
                    title: '上传成功'
                  })
                  setTimeout(function(){
                    wx.hideToast({
                      success: (res) => {
                        that.setData({
                          maskFlag:true
                         })
                        wx.navigateBack({
                          delta: 1,
                        })
                      },
                    })
                  },1500)
               
                },
              })
              
        
           }
            })
        })
      }
   },
   //提交卖房
   dopostSell:function(e){
    let that = this;
      //上传图片逻辑
      //没有图片的提交
      if (!that.data.imgbox.length){
       wx.showToast({
         icon:"none",
         title: '请至少添加一张图片'
       })
      } else { //有图片的提交
          //提交效果
          wx.showLoading({
            title: '上传中',
          })
          that.setData({
            'maskFlag':false
          })
          //提交逻辑
          let promiseArr = [];
          var urls = that.data.urls;
          for (let i = 0; i < that.data.imgbox.length; i++) {
            var p = new Promise((reslove, reject) => {
                let item = that.data.imgbox[i];
                console.log("准备上传111111111111"+item);
                qiniuUploader.upload(item, (res) => {
                  //获得url
                  console.log('file url is: ' + res.fileURL);
                  if (urls.length == 0) {
                    urls=new Array(res.fileURL);
                  } else{
                    urls = urls.concat(res.fileURL);
                  }
                  reslove();
              }, (error) => {
                reject();
                console.error('error: ' + JSON.stringify(error));
              }, null,
              (progress) => {
                  console.log('上传进度', progress.progress);
              }, cancelTask => that.setData({ cancelTask }));
            })
            promiseArr.push(p);
          };
          Promise.all(promiseArr).then(res => {//等数组都做完后做then方法
            that.setData({
              urls:urls
            })
            console.log("图片上传完成后再执行后台数据库交互")
            console.log("urls",that.data.urls);
            wx.request({
               url: 'http://47.94.170.167:8080/insertSellHouse',
              //  url:'http://localhost:8080/insertSellHouse',
                data:{
                  'host_id':wx.getStorageSync('openId'),
                  "address":e.detail.value.address,
                  "title":e.detail.value.title,
                  "property":e.detail.value.property,
                  "type":e.detail.value.type,
                  "orientation":e.detail.value.orientation,
                  "floor":e.detail.value.floor,
                  "is_renovation":e.detail.value.is_renovation,
                  "is_elevator":e.detail.value.is_elevator,
                  "area":e.detail.value.area,
                  "furniture":e.detail.value.furniture,
                  "price":e.detail.value.price,
                  "images":that.data.urls
                },
                method:'POST',
                header: {
                'Content-Type': 'application/json'
                },
                success: res =>{
                  console.log("数据库成功加入rent id为-> "+res.data);
                    that.setData({
                    imgbox:[]
                  })
                  wx.hideLoading({
                    success: (res) => {
                      console.log("提交结束");
                      wx.showToast({
                        title: '上传成功'
                      })
                      setTimeout(function(){
                        wx.hideToast({
                          success: (res) => {
                            that.setData({
                              maskFlag:true
                             })
                            wx.navigateBack({
                              delta: 1,
                            })
                          },
                        })
                      },1500)
                    },
                  })
                  
            
               }
            })
          })
      }
     
   },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
    wx.request({
     // url:  "http://localhost:8080/qiniuimg/token",
      url:"http://47.94.170.167:8080/qiniuimg/token",
      method: "get",
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: res => {
        console.log(res.data)
        wx.setStorageSync("upLoadToken", res.data.uptoken);//将token保存到缓存中
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

  },
  //删除图片
  imgDelete1: function (e) {
    let that = this;
    let index = e.currentTarget.dataset.deindex;
    let imgbox = this.data.imgbox;
    imgbox.splice(index, 1)
    that.setData({
      imgbox: imgbox
    });
  },
  //选择图片
  addPic1: function (e) {
    var imgbox = this.data.imgbox;
    console.log(imgbox)
    var that = this;
    var n = 5;
    if (5 > imgbox.length > 0) {
      n = 5 - imgbox.length;
    } else if (imgbox.length == 5) {
      n = 1;
    }
    //初始化七牛云
    initQiniu();
    wx.chooseImage({
      count: n, // 默认9，设置图片张数
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
         console.log(res.tempFilePaths)
         console.log(qiniuUploader)
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths
        if (imgbox.length == 0) {
          imgbox = tempFilePaths
        } else if (5 > imgbox.length) {
          imgbox = imgbox.concat(tempFilePaths);
        }
        that.setData({
          imgbox: imgbox
        });
        console.log("本地图片",that.data.imgbox);
      }
    })
  },
  //图片
  imgbox: function (e) {
    this.setData({
      imgbox: e.detail.value
    })
  }
})

