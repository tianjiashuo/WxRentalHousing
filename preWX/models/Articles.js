import Http from '../utils/Http';
import url from '../utils/url';
import Cache from '../utils/Cache';

class Model extends Http{
    // 咨询列表
    getList(page = 1) {
        return this.httpRequest({
            url: url.articles,
            data: { page }
        });
    }

    detail(article){
        return this.httpRequest({
            url:url.articles + '/' + article
        })
    }

    articlesCount(art_id) {
        return this.httpRequest({
            url: url.articlesCount,
            data: {
                rid: Cache.get('id'),
                art_id
            },
            method: "POST"
        });
    }
}

export default new Model;