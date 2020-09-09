import Http from '../utils/Http';
import url from '../utils/url';
import Cache from '../utils/Cache';

class Model extends Http {
    // 获取数据
    getRecommend() {
        return this.httpRequest({
            url: url.recommend
        });
    }
    // 房源属性
    getAttrList(field_name) {
        return this.httpRequest({
            url: url.attr,
            data: { field_name }
        });
    }

    // 房源列表
    getFangs(page = 1, attrField = {}) {
        return this.httpRequest({
            url: url.fangs,
            data: { page,attrField }
        });
    }

    // 房源详情
    getFangInfo(fang) {
        return this.httpRequest({
            url: `${url.fangs}/${fang}`
        });
    }

    // 是否收藏
    isFav(fang_id) {
        return this.httpRequest({
            url: url.isfav,
            data: {
                rent_id: Cache.get('id'),
                fang_id
            }
        });
    }

    // 添加收藏/取消收藏
    addFav(fang_id, fav) {
        return this.httpRequest({
            url: url.fav,
            data:{
                rent_id: Cache.get('id'),
                fang_id,fav
            },
            method:"POST"
        });
    }

    // 收藏列表
    favList(page) {
        return this.httpRequest({
            url: url.fav,
            data: {
                rent_id: Cache.get('id'),
                page
            }
        });
    }

    // 添加看房记录
    addRec(fang_id) {
        return this.httpRequest({
            url: url.addfangrec,
            method: "POST",
            data: {
                rid: Cache.get('id'),
                fang_id
            }
        });
    }

    // 看房记录列表
    recList(page) {
        return this.httpRequest({
            url: url.addfangrec,
            data: {
                rid: Cache.get('id'),
                page
            }
        });
    }

    // 个人已租房列表
    rented(page) {
        return this.httpRequest({
            url: url.myhome,
            data:{
                rent_id: Cache.get('id'),
                page
            }
        });
    }

    // 快搜房源属性
    quickAttr() {
        return this.httpRequest({
            url: url.qattr
        });
    }

    search(kw, page = 1) {
        return this.httpRequest({
            url: url.search,
            data: {
                kw,page
            }
        });
    }
    
}

export default new Model;