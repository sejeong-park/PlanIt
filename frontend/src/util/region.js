const region = 
    [
        {
            label : '전국',
            value : '0'
        },
        {
            label: '서울',
            value: '1',
            children: [
                {value: '0', label: '전체'},
                {value: '1', label: '강남구'},
                {value: '2', label: '강동구'},
                {value: '3', label: '강북구'},
                {value: '4', label: '강서구'},
                {value: '5', label: '관악구'},
                {value: '6', label: '광진구'},
                {value: '7', label: '구로구'},
                {value: '8', label: '금천구'},
                {value: '9', label: '노원구'},
                {value: '10', label: '도봉구'}
            ]
        },
        {
            label: '인천',
            value: '2', 
            children: [
                {value: '0', label: '전체'},
                {value: '1', label: '강화군'},
                {value: '2', label: '계양구'},
                {value: '3', label: '미추홀구'},
                {value: '4', label: '남동구'},
                {value: '5', label: '동구'},
                {value: '6', label: '부평구'},
                {value: '7', label: '서구'},
                {value: '8', label: '연수구'},
                {value: '9', label: '옹진군'},
                {value: '10', label: '중구'}
            ]
        },
        {
            label: '대전',
            value: '3',
            children: [
                {value: '0', label: '전체'},
                {value: '1', label: '대덕구'},
                {value: '2', label: '동구'},
                {value: '3', label: '서구'},
                {value: '4', label: '유성구'},
                {value: '5', label: '중구'}
            ]},
        {
            label: '대구',
            value: '4', 
            children: [
                {value: '0', label: '전체'},
                {label: '남구', value: '1'},
                {label: '달서구', value: '2'},
                {label: '달성군', value: '3'},
                {label: '동구', value: '4'},
                {label: '북구', value: '5'},
                {label: '서구', value: '6'},
                {label: '수성구', value: '7'},
                {label: '중구', value: '8'},
                {label: '군위군', value: '9'}
            ]
        },
        {
            label: '광주',
            value: '5',
            children: [
                {value: '0', label: '전체'},
                {value: '1', label: '광산구'},
                {value: '2', label: '남구'},
                {value: '3', label: '동구'},
                {value: '4', label: '북구'},
                {value: '5', label: '서구'}
            ]
        },
        {
            label: '부산',
            value: '6', 
            children: [
                { value: "1", label: "강서구"},
                { value: "2", label: "금정구"},
                { value: "3", label: "기장군"},
                { value: "4", label: "남구"},
                { value: "5", label: "동구"},
                { value: "6", label: "동래구"},
                { value: "7", label: "부산진구"},
                { value: "8", label: "북구"},
                { value: "9", label: "사상구"},
                { value: "10", label: "사하구"}
            ]
        },
        {
            label: '울산',
            value: '7',
            children: [
                {value: '0', label: '전체'},
                {label: '중구', value: '1'},
                {label: '남구', value: '2'},
                {label: '동구', value: '3'},
                {label: '북구', value: '4'},
                {label: '울주군', value: '5'}
            ]
        },
        {
            label: '세종특별자치시',
            value: '8',
            children: [
                {value: '0', label: '전체'},
                {label : '세종특별자치시', value : '1'}
            ]
        },
        {
            label: '경기도', 
            value: '31',
            children: [
                {label : '전체', value: '0'},
                {value: '1',label: "가평군"},
                {value: '2',label: "고양시"},
                {value: '3',label: "과천시"},
                {value: '4',label: "광명시"},
                {value: '5',label: "광주시"},
                {value: '6',label: "구리시"},
                {value: '7',label: "군포시"},
                {value: '8',label: "김포시"},
                {value: '9',label: "남양주시"},
                {value: '10',label: "동두천시"}
            ]},
        {
            label: '강원특별자치도',
            value: '32', 
            children: [
                {value: '0', label: '전체'},
                {label: '강릉시', value: '1'},
                {label: '고성군', value: '2'},
                {label: '동해시', value: '3'},
                {label: '삼척시', value: '4'},
                {label: '속초시', value: '5'},
                {label: '양구군', value: '6'},
                {label: '양양군', value: '7'},
                {label: '영월군', value: '8'},
                {label: '원주시', value: '9'},
                {label: '인제군', value: '10'}
            ]
        }
    ]

export default region;
