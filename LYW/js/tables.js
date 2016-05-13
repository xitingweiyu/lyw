/**
 * Created by lyw on 2016/5/13.
 */
function tabClick(){
    this.defaults = {
           id  : null,
        active : 1,
        timeout : null,
        interval:null,
        tabclass:'tab',
        activeclass:'active'
    };

    //初始化每个实例对象
    for (var n in arguments[0]){this.defaults[n] = arguments[0][n];};

    //获取class为tab的标签
    this.getTabs = function() {
        var retnode = [];
        var elem = document.getElementById(this.defaults.id).getElementsByTagName('*');
        //去掉收尾空格
        var regexp = new RegExp("(^|\\s)" + this.defaults.tabclass.replace(/\-/g, "\\-") + "(\\s|$)");
        for (var i = 0; i < elem.length; i++) {
            if (regexp.test(elem[i].className)) retnode.push(elem[i]);
        }
        return retnode;
    };


    //获取左边目录索引
    this.links = document.getElementById(this.defaults.id + '_nav').getElementsByTagName('a');

    //显示表
    this.show = function(number){
        for(var i = 0; i < this.getTabs().length;i++){
            this.tabs[i].style.display = ((i+1)==number) ? 'block':'none';
            this.links[i].className = ((i+1)==number) ? this.defaults.activeclass : '';
        }
    }

    this.tabs = this.getTabs();
    this.show(this.defaults.active);

    var self = this;
    for (var i = 0; i < this.links.length; i++) {
        this.links[i].customindex = i+1;
        this.links[i].onclick = function(){
            if (self.defaults.timeout)
                clearTimeout(self.defaults.timeout);
                self.show(this.customindex);
            return false;
        };
    }



}