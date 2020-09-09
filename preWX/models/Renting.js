import Http from '../utils/Http';
import url from '../utils/url';
import Cache from '../utils/Cache';

class Model extends Http {
    setUserInfo(data) {
        return this.httpRequest({
            url: url.setuserinfo,
            data,
            method: "POST"
        });
    }

    // 根据用户id 获取用户数据
    getUserInfo(){
        return this.httpRequest({
            url: url.renting + '/' + Cache.get('id')
        })
    }

    // 修改数据
    updateUserInfo(data){
        return this.httpRequest({
            url: url.renting,
            data: { id: Cache.get('id'), ...data },
            method: "POST"
        });
    }

    upfile(obj){
        wx.chooseImage({
        // 可上传的图片数量
        count: 3,
        // 原图和压缩
        sizeType: ['original','compressed'],
        // 相册或相机
        sourceType: ['album','camera'],
        success: res => {
          // 图片临时存储地址，用于小程序展示
          let imgList = res.tempFilePaths;
          // 修改数据源
          obj.setData({ 
            userinfo: { ...obj.data.userinfo, img_list: imgList }
           });
          // 循环
          imgList.forEach(filePath => {
            this.uploadFile({
              url: url.rentingupfile,
              filePath
            }).then( ret => {
              let json = JSON.parse(ret.data);
              let card_img = obj.data.userinfo.card_img + '#' + json.pic;
              obj.setData({
                  userinfo:{ ...obj.data.userinfo, card_img }
              })
            });
            
          });
        }
      });
    }
}

export default new Model;