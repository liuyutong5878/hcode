/** 
 * Copyright 2014, Kugou.com 
 * LastChange: Thursday, July 31, 2014, 18:19:29
 * Compressed By Grunt 
 */
!
function() {
    LRC = function() {
        this.initialize.apply(this, arguments)
    },
    LRC.prototype = {
        arrLyricTime: [],
        arrLyric: [],
        initialize: function(a) {
            this.lyricWrapper = a.lyricWrapper,
            this.curRowClassName = a.curRowClassName,
            this.separator = a.separator,
            this.wordTime = -1,
            this.arrLyricTime = [],
            this.arrLyric = [],
            this.initArray(a.lyric),
            this.arrLyricTime = this.sort(this.arrLyricTime),
            this.setLyricTable(this.arrLyric)
        },
        initArray: function(a) {
            for (var b = new RegExp("\\[[0-9:.]*\\]", "g"), c = a.split(this.separator), d = 0, e = 0, f = 0; f < c.length; f++) {
                var g = c[f].replace(/\[[\w\W]*\]/g, "").trim();
                if ("" !== g) for (this.arrLyric[f - e] = g; null !== (lrc_result = b.exec(c[f]));) {
                    var h = this.parseSecond(lrc_result.toString().replace(/\[|\]/g, ""));
                    isNaN(h) || (this.arrLyricTime[d] = f - e + "|" + h, d++)
                } else e++
            }
        },
        IsLyricValid: function() {
            return this.arrLyricTime.length > 0
        },
        DoSync: function(a) {
            clearInterval(this.wordTime);
            for (var b = this.arrLyricTime,
            c = 0; c < b.length; c++) {
                var d = b[c].split("|");
                if (0 === c && a < d[1]) break;
                if ("undefined" == typeof b[c + 1]) {
                    this.setRow(d[0]);
                    break
                }
                var e = b[c + 1].split("|");
                if (a >= d[1] && a < e[1]) {
                    this.setRow(d[0]);
                    break
                }
            }
        },
        parseSecond: function(a) {
            try {
                var b = a.split(":");
                return 60 * parseInt(b[0], 10) + parseFloat(b[1])
            } catch(c) {
                return 0
            }
        },
        setLyricTable: function(a) {
            var b = "";
            this.lyricWrapper.scrollTop = 0,
            this.lyricWrapper.innerHTML = "";
            for (var c = 0; c < a.length; c++) b += "<p>" + a[c] + "</p>";
            this.lyricWrapper.innerHTML = b;
            var d = this.lyricWrapper.getElementsByTagName("p"),
            e = d[0].offsetHeight,
            f = 0;
            this.outLrc || (this.outLrc = this.lyricWrapper.parentNode),
            f = Math.floor(this.outLrc.clientHeight / e),
            this.lyricWrapper.style.height = e * f + "px"
        },
        setRow: function(a) {
            this.setRowClass(a),
            this.setScroll(a)
        },
        setRowClass: function(a) {
            for (var b = this.lyricWrapper.getElementsByTagName("p"), c = 0; c < b.length; c++) b[c].className = "";
            b[a].className = this.curRowClassName,
            b[a].scrollWidth > b[a].offsetWidth && (this.wordTime = setInterval(function() {
                b[a].scrollLeft++
            },
            1e3))
        },
        setScroll: function(a) {
            var b = this.lyricWrapper.getElementsByTagName("p"),
            c = b[0].offsetHeight,
            d = 0;
            this.outLrc || (this.outLrc = this.lyricWrapper.parentNode),
            d = Math.floor(this.outLrc.clientHeight / c),
            this.lyricWrapper.style.height = c * d + "px",
            this.lyricWrapper.scrollTop = b[a].offsetTop - b[a].offsetHeight * Math.floor(d / 2)
        },
        sort: function(a) {
            for (var b = 0; b < a.length - 1; b++) for (var c = b + 1; c < a.length; c++) {
                var d = parseFloat(a[b].split("|")[1]),
                e = parseFloat(a[c].split("|")[1]);
                if (d > e) {
                    var f = a[b];
                    a[b] = a[c],
                    a[c] = f
                }
            }
            return a
        }
    };
    var a = function() {
        return this instanceof a ? void 0 : new a
    };
    a.prototype = {
        extend: function(a, b, c) {
            for (var d in b) c ? a[d] = b[d] : a[d] || (a[d] = b[d]);
            return a
        },
        formatNumber: function(a) {
            return a.toString().length < 2 ? "0" + a: a
        },
        formatData: function(a) {
            a = parseInt(a, 10);
            var b = this.formatNumber(Math.floor(a / 60)),
            c = this.formatNumber(Math.floor(a % 60));
            return b + ":" + c
        },
        addZero: function(a) {
            return a.toString().length <= 1 ? "0" + a: a
        },
        isIOS: function() {
            return navigator.userAgent.match(/iPad/i) || navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPod/i) ? !0 : !1
        },
        isKnowAdr: function() {
            return navigator.userAgent.match(/HUAWEI/i) || navigator.userAgent.match(/Chrome/i) ? !0 : !1
        },
        isCanPlayAudio: function() {
            return document.createElement("audio").canPlayType("audio/mpeg") ? !0 : !1
        },
        formatImgSize: function(a) {
            size = a.size || 400;
            var b = a.img;
            return b = b.replace(/softhead\/\d+\//, "softhead/" + size + "/")
        }
    };
    var b = function(c) {
        var d = this;
        return this instanceof b ? (d.utils = a(), d.init(c), this) : new b(c)
    };
    b.prototype = {
        ver: "1",
        cacheList: {},
        _default: {
            fatherId: "player",
            playId: "kugou",
            currentTime: "current_time",
            totalTime: "total_time",
            btnNext: "play_next",
            btnMain: "play_main",
            playClass: "play",
            pauseClass: "pause",
            loadClass: "load",
            btnMode: "play_mode",
            songName: "song_name",
            singerName: "singer_name",
            fullName: "songname",
            playBarPlay: "play_bar_play",
            playBarLoad: "play_bar_load",
            songLrc: "song_lrc",
            isShowLrc: !1,
            songCover: "song_cover",
            defCover: "http://m.kugou.com/static/images/share2014/list/cover.png",
            isSongCover: !1,
            songCls: "songsList",
            curPlayCls: "cur",
            songsListExt: "songs_",
            loadName: "loading",
            isCycle: !0,
            isAuto: !1,
            playMode: "list",
            isPlayRadio: !1,
            isPlayRing: !1
        },
        nextSong: function() {
            var a = this;
            curIndex = a.curIndex,
            a._default.isPlayRadio && a.getFmSongs(),
            a.curIndex++,
            a.curIndex < a.songsList.length ? a.create() : (a.curIndex = 0, a.create())
        },
        getFmSongs: function() {},
        cusload: function() {},
        cusPlay: function() {},
        loadStart: function() {
            var a = this;
            a.utils.isIOS() || a.utils.isKnowAdr() ? a.isFirst || !a.isAuto ? a.cusload() : a.isFirst = !0 : a.cusload()
        },
        playStart: function(a) {
            var b = this;
            b.cusPlay(a)
        },
        play: function() {
            try {
                clearInterval(this.playSt),
                clearInterval(this.loadSt)
            } catch(a) {}
            var b = this,
            c = document;
            c.getElementById(b._default.playId) && c.getElementById(b._default.playId).play(),
            c.getElementById(b._default.btnMain) && (c.getElementById(b._default.btnMain).className = b._default.pauseClass),
            b.loadst = setInterval(function() {
                b.setLoadProgress()
            },
            1e3)
        },
        pause: function() {
            var a = this,
            b = document;
            try {
                b.getElementById(a._default.playId).pause()
            } catch(c) {}
        },
        setLoadProgress: function() {
            var a = this;
            try {
                var b = Math.ceil(document.getElementById(a._default.playId).duration);
                document.getElementById(a._default.playBarLoad).style.width = a.getCurrentBufferedTime() / b * 100 + "%",
                Math.ceil(a.getCurrentBufferedTime()) >= b && a.clearInterval(a.loadSt)
            } catch(c) {}
        },
        setPlayProgress: function() {
            var a = this,
            b = {};
            b = a.songsList[a.curIndex];
            try {
                document.getElementById(a._default.playBarPlay).style.width = a.getCurrentTime() / b.timeLength * 100 + "%"
            } catch(c) {}
        },
        create: function() {
            var a = this,
            b = document,
            c = {},
            d = "",
            e = "";
            if (!a.isLoadSrc) {
                if (a.isLoadSrc = !0, c = a.songsList[a.curIndex], b.getElementById(a._default.playId).removeAttribute("src"), !a.utils.isCanPlayAudio()) return void alert("此款手机不支持HTML5播放"); ! c.hash || a._default.isPlayRadio || a._default.isPlayRing || (e = a._default.songsListExt + c.hash.toUpperCase(), Kg.$("." + a._default.songCls).removeClass(a._default.curPlayCls), Kg.$("#" + e).addClass(a._default.curPlayCls)),
                a.cacheList[c.hash] ? d = a.cacheList[c.hash].src: a._default.isPlayRing ? (d = c.songurl, a.songsList[a.curIndex].src = d, a.songsList[a.curIndex].timeLength = 0, a.songsList[a.curIndex].filename = c.filename) : c.hash ? Kg.getJSON("http://m.kugou.com/app/i/getSongInfo.php", {
                    cmd: "playInfo",
                    hash: c.hash
                },
                function(b) {
                    a.songsList[a.curIndex].hash && a.songsList[a.curIndex].hash.toUpperCase() != b.hash.toUpperCase() || b && 1 == b.status && (d = b.url, a.songsList[a.curIndex].src = d, a.songsList[a.curIndex].timeLength = b.length, a.songsList[a.curIndex].filename = unescape(c.filename) || b.fileName, a.cacheList[c.hash] = {
                        src: d,
                        timeLength: b.length,
                        filename: unescape(c.filename) || b.fileName
                    })
                },
                "", !1) : (d = c.songurl, a.songsList[a.curIndex].src = d, a.songsList[a.curIndex].timeLength = c.timeLength, a.songsList[a.curIndex].filename = c.filename),
                a.utils.isIOS() || a.utils.isKnowAdr() ? b.getElementById(a._default.playId).src = d: (b.getElementById(a._default.fatherId).innerHTML = '<audio id="' + a._default.playId + '" src="' + d + '" autoplay="autoplay"></audio>', a.bindAudio()),
                a.utils.isIOS() && b.getElementById(a._default.playId).pause(),
                b.getElementById(a._default.playId).play(),
                b.getElementById(a._default.songLrc) && a.getLrc(),
                a.setSongHead(),
                phpLogClick(4241)
            }
        },
        setSongHead: function() {
            var a = this,
            b = document,
            c = "",
            d = 0,
            e = {};
            e = a.songsList[a.curIndex],
            e.filename && (c = e.filename.split(/\s?-\s?/), c.length < 2 && (c = e.filename.split(/-/))),
            d = e.timeLength,
            a._default.isSongCover && (document.title = e.filename ? e.filename + " - 酷狗音乐 让音乐改变世界": "酷狗音乐 让音乐改变世界"),
            b.getElementById(a._default.fullName) && (b.getElementById(a._default.fullName).innerHTML = e.filename),
            b.getElementById(a._default.songName) && c[1] && (b.getElementById(a._default.songName).innerHTML = c[1]),
            b.getElementById(a._default.singerName) && c[0] && (b.getElementById(a._default.singerName).innerHTML = c[0]),
            b.getElementById(a._default.currentTime) && (b.getElementById(a._default.currentTime).innerHTML = "00:00", b.getElementById(a._default.totalTime).innerHTML = a.utils.addZero(Math.floor(d / 60)) + ":" + a.utils.addZero(Math.floor(d % 60))),
            b.getElementById(a._default.playBarPlay) && (b.getElementById(a._default.playBarPlay).style.width = "0"),
            b.getElementById(a._default.playBarLoad) && (b.getElementById(a._default.playBarLoad).style.width = "0"),
            a._default.isSongCover && Kg.getJSON("http://m.kugou.com/app/i/getSingerHead_new.php", {
                singerName: c[0],
                size: d
            },
            function(b) {
                var c = a._default.defCover;
                b && (c = a.utils.formatImgSize({
                    img: b.url
                })),
                document.getElementById(a._default.songCover) && (document.getElementById(a._default.songCover).src = c)
            })
        },
        getBufferedTime: function() {
            var a = this,
            b = {};
            b = a.songsList[a.curIndex];
            var c = Math.ceil(a.getCurrentBufferedTime());
            return c > b.timeLength && (c = b.timeLength),
            c
        },
        getPlayTime: function() {
            var a = this,
            b = {};
            b = a.songsList[a.curIndex];
            var c = Math.ceil(a.getCurrentTime());
            c > b.timeLength && (c = b.timeLength);
            var d = Math.floor(c / 60),
            e = c % 60;
            return a.utils.addZero(d) + ":" + a.utils.addZero(e)
        },
        getCurrentTime: function() {
            try {
                return document.getElementById(this._default.playId).currentTime
            } catch(a) {}
            return 0
        },
        getCurrentBufferedTime: function() {
            try {
                return document.getElementById(this._default.playId).buffered.end(0)
            } catch(a) {}
            return 0
        },
        addSongsAndPlay: function(a) {
            var b = this;
            a && (b.songsList = a.write ? a.songs: b.songsList.concat(a.songs), b.songsList.length > 0 && (b.curIndex = 0, b.create()))
        },
        addSongsNoPlay: function(a) {
            var b = this;
            a && (b.songsList = a.write ? a.songs: b.songsList.concat(a.songs))
        },
        getLrc: function() {
            try {
                clearInterval(this.lrcSt)
            } catch(a) {}
            var b = this,
            c = document,
            d = {},
            e = {};
            e = b.songsList[b.curIndex],
            e.lrc ? (b.uiLRC = new LRC({
                lyric: e.lrc,
                lyricWrapper: c.getElementById(b._default.songLrc),
                curRowClassName: "current",
                separator: "\r\n"
            }), b.getLrcStatus()) : (d = {
                cmd: 100,
                keyword: unescape(e.filename),
                hash: e.hash,
                timelength: 1e3 * e.timeLength,
                d: Math.random()
            },
            Kg.get("http://m.kugou.com/app/i/krc.php", d,
            function(a) {
                a && "" !== a.trim() ? (b.uiLRC = new LRC({
                    lyric: a,
                    lyricWrapper: c.getElementById(b._default.songLrc),
                    curRowClassName: "current",
                    separator: "\r\n"
                }), b.getLrcStatus()) : c.getElementById(b._default.songLrc).innerHTML = '<div class="nolrc">酷狗音乐，让音乐改变世界！</div>'
            }))
        },
        getLrcStatus: function() {
            var a = this;
            a.uiLRC && a.uiLRC.IsLyricValid() && (a.lrcSt = setInterval(function() {
                a.uiLRC.DoSync(a.getCurrentTime())
            },
            200))
        },
        bindAudio: function() {
            var a = this,
            b = document;
            a.isInClient || (Kg.$("#" + a._default.playId).addEvent("ended",
            function() {
                a._default.isCycle && "list" == a._default.playMode ? a.nextSong() : a._default.isCycle && document.getElementById(a._default.playId).play()
            }), Kg.$("#" + a._default.playId).addEvent("pause",
            function() {
                b.getElementById(a._default.btnMain) && (b.getElementById(a._default.btnMain).className = a._default.playClass),
                a.playStart("pause")
            }), Kg.$("#" + a._default.playId).addEvent("play",
            function() {
                b.getElementById(a._default.btnMain) && (b.getElementById(a._default.btnMain).className = a._default.pauseClass),
                a.playing && a.playing()
            }), Kg.$("#" + a._default.playId).addEvent("error",
            function() {
                a.playStart("error")
            }), Kg.$("#" + a._default.playId).addEvent("timeupdate",
            function() {
                b.getElementById(a._default.currentTime) && (b.getElementById(a._default.currentTime).innerHTML = a.getPlayTime()),
                a.setPlayProgress(),
                a.getLrcStatus()
            }), Kg.$("#" + a._default.playId).addEvent("loadstart",
            function() {
                a.loadStart(),
                a.isLoadSrc = !1
            }), Kg.$("#" + a._default.playId).addEvent("canplay",
            function() {
                a.isLoadSrc = !1,
                a.playStart()
            }))
        },
        bind: function() {
            var a = this,
            b = document;
            b.getElementById(a._default.btnMain) && Kg.$("#" + a._default.btnMain).addEvent("click",
            function() {
                a.songsList && a.songsList.length > 0 && -1 != a.curIndex && (this.className.indexOf(a._default.playClass) > -1 ? a.play() : a.pause())
            }),
            b.getElementById(a._default.btnNext) && Kg.$("#" + a._default.btnNext).addEvent("click",
            function() {
                a.nextSong()
            }),
            a.bindAudio()
        },
        createAudio: function() {
            var a = this,
            b = document;
            if (!b.getElementById(a._default.fatherId)) {
                var c = document.createElement("div");
                c.id = a._default.fatherId,
                c.style.display = "none",
                c.innerHTML = a._default.isAuto ? '<audio id="' + a._default.playId + '" autoplay height="100%" width="100%" controls></audio>': '<audio id="' + a._default.playId + '" height="100%" width="100%" controls></audio>',
                b.body.appendChild(c)
            }
        },
        init: function(a) {
            var b = this;
            return b.songsList = [],
            b.curIndex = -1,
            b.uiLRC = null,
            a && b.utils.extend(b._default, a, !0),
            b.createAudio(),
            b.bind(),
            this
        }
    },
    window.Player = b
} ();