const mockupdata = [
    {
        title : "청양군",
        info1 : "얼음 축제가 열리는 특별한 겨울왕국",
        info2 : "알프스마을, 천장호출렁다리",
        image : "https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fblogfiles.naver.net%252FMjAyMjEyMDlfMTE0%252FMDAxNjcwNTUzNTY1MzQ0.i-5xvukGzUaklxmBQ_N9ZTXlqNNT5nbNWY2mjPnvJ-8g.-bj1xzJqvLSV9O8Lv66HDokpsaaEeLYWsBHcLo48pmog.JPEG.hye_won0324%252FBeautyPlus%2525A3%2525DF20220210131507326%2525A3%2525DFsave.jpg%26type%3Dsc960_832&type=f1040_576_domesearch"
    },
    {
        title : "인제군",
        info1 : "겨울이면 하얀 눈과 흰 나무가 빛나는 풍경을 자랑하는 원대리 자작나무 숲",
        info2 : "",
        image : "https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fblogfiles.naver.net%252FMjAyMzExMDVfMjgy%252FMDAxNjk5MTY5MjUxMjc4.T5_qGxGSUQOOJrOdAG7wDfCSnp4upBzvrcWwU-z2BT0g.brZEcYy4KEJL0ZPpFnGjeKGarGqtBeNuoHmTU9xLxkkg.JPEG.csg0803%252F3U5A4892-2.jpg%26type%3Dsc960_832&type=f1040_576_domesearch"
    },
    {
        title : "무주군",
        info1 : "여권 없이 떠나는 스키 천국 무주",
        info2 : "",
        image : "https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fblogfiles.naver.net%252F20131225_297%252Falice8565_1387918936115JlkkC_JPEG%252FIMG_6249.jpg%26type%3Dsc960_832&type=f1040_576_domesearch"
    },
    {
        title : "이천시",
        info1 : "긴 겨울밤을 따듯하고 반짝이게 보낼 수 있는 이천",
        info2 : "",
        image : "https://search.pstatic.net/common?src=https%3A%2F%2Fpostfiles.pstatic.net%2FMjAxODExMjZfMTAx%2FMDAxNTQzMjA4NzgwNzY1.sNs8EnW-KnCdIEvbsvXnl0OQTTP0HBkukrwnkvmAk2Qg.O-T5O0Q1yjV-D5mazwI5cDQ6PwBGA1E9MAd20-IsSGcg.JPEG.smartinfini1%2F%25ED%2585%258C%25EB%25A5%25B4%25EB%25A9%2594%25EB%258D%25B4%25EC%2595%25BC%25EC%2599%25B8%25EC%2598%25A8%25EC%25B2%259C%25ED%2592%2580_%25EA%25B2%25A8%25EC%259A%25B8_%25EC%2584%25A4%25EA%25B2%25BD12.jpg%3Ftype%3Dw966&type=f1040_576_domesearch"
    },
    {
        title : "진해구",
        info1 : "진해에서 즐기는 늦가을의 정취",
        info2 : "",
        image : "https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fblogfiles.naver.net%252FMjAyMzExMTlfMTUy%252FMDAxNzAwMzY0MTg4OTI3.ipbRMH1mSfTOks2rgxnApXkIFh-tSANr40_i-Etm60Qg.fPn03hZW2rk34TpC_ZLAFgXL9xF_kgwAybfHnC46qr0g.JPEG.kimsui2018%252FDSC%2525A3%2525DF0503%2525A3%2525AD1.jpg%26type%3Dsc960_832&type=f1040_576_domesearch"
    },
    {
        title : "광양시",
        info1 : "빛의 도시 광양에서만 만날 수 있는 특별한 별빛 축제",
        info2 : "",
        image : "https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fimage.nmv.naver.net%252Fblog_2022_07_07_348%252F4dd7751a-fd41-11ec-81b1-505dac8c3639_01.jpg%26type%3Dsc960_832&type=f1040_576_domesearch"
    },
    {
        title : "예산군",
        info1 : "아이들과 함게 떠나는 신나는 겨울 나들이",
        info2 : "",
        image : "https://search.pstatic.net/common?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20220107_123%2F1641514826191RoN9q_JPEG%2F%25C5%25E4%25B7%25BB%25C6%25AE%25B8%25AE%25B9%25F61_%25C0%25FA.jpg&type=f1040_576_domesearch"
    },
    {
        title : "창녕궁",
        info1 : "태고의 자연과 역사가 공존하는 창녕으로 떠나요.",
        info2 : "",
        image : "https://search.pstatic.net/common?src=https%3A%2F%2Fpostfiles.pstatic.net%2FMjAyMzEwMjNfMTQ0%2FMDAxNjk3OTk1ODgzNzgy.v6AbDqdMljZo7w4Mjw1e4js7tJ4AtG5ccvRY5DAGewEg.KVG6CeF0kvcKgk7NI3b7oPEao2yP0SaueVccpF0bUykg.JPEG.misodrama%2FHUN_0257.jpg%3Ftype%3Dw773&type=f1040_576_domesearch"
    },
    {
        title : "춘천시",
        info1 : "호반의 도시 춘천으로 떠나는 낭만 가을여행",
        info2 : "",
        image : "https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fblogfiles.naver.net%252FMjAyMzEwMjVfOTUg%252FMDAxNjk4MjQ0OTc0OTM5.3SmNJBaY06F-yD1LGe7-uSdh8bUuFFOeCsRwUZrXwucg.e9coYWv4ugYxf2gzZDlFP4rT65BM_qciBCmXOgqfnFYg.JPEG.108trouble%252F%2525C3%2525E1%2525C3%2525B5_%2525B0%2525A1%2525BA%2525BC%2525B8%2525B8%2525C7%2525D1%2525B0%2525F7_%2525C3%2525BB%2525C6%2525F2%2525BB%2525E7_%2525B4%2525DC%2525C7%2525B3_%2525B1%2525B9%2525B8%2525B3%2525C3%2525E1%2525C3%2525B5%2525BD%2525A3%2525C3%2525BC%2525BF%2525F8_1%2525B9%2525DA2%2525C0%2525CF_%2525BF%2525A9%2525C7%2525E0%2525C4%2525DA%2525BD%2525BA_%25252829%252529.jpg%26type%3Dsc960_832&type=f1040_576_domesearch"
    },
    {
        title : "청도군",
        info1 : "아름다운 빛 축제가 열리는 로맨틱한 하루",
        info2 : "",
        image : "https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fblogfiles.naver.net%252FMjAyMzEwMDdfMjIy%252FMDAxNjk2NjgwOTAxMDY5.VTKDIDQ1flcvE-y6325x9Y0gyLHsvJ0fTmQRlLF3hAUg.hDi_m-qGelC87iU38_iBW3IY4gcX_k94TYTNAZLh_i4g.JPEG.ssaanee%252F1696680857341.jpg%26type%3Dsc960_832&type=f520_288_domesearch"
    }
]
export default mockupdata;
