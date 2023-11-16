

function convertDateFormat(dateStr, date_format) {
    // input "YYYY-MM-DD"
    // 데이터 포맷을 요구하는 형식이 다양해서 지정한 형식으로 변환하기 위한 코드

    const parts = dateStr.split('-'); // 섹션 나누기
    const year = parts[0].substring(2); // 연도의 뒤 두자리
    const month = parts[1]; // 월
    const day = parts[2]; // 일

    // 필요에 따라 dateTime 을 바꿉니다.
    if (date_format == 'YY.MM.DD') {
        return `${year}.${month}.${day}`;
    }else if(date_format == 'YYYYMMDD') {
        return `${parts[0]}${month}${day}`;
    }else if (date_format == 'YYYY-MM-DD') {
        return `${parts[0]}-${month}-${day}`;
    }
}

function calculateDaysBetweenDates(startDate, endDate) {
    // input : "YYYY-MM-DD"
    // startDate와 endDate 가 여행 일정이 몇일인지 구하는 함수
    const start = new Date(startDate);
    const end = new Date(endDate);
    const timeDifference = end.getTime() - start.getTime();
    const dayDifference = timeDifference / (1000 * 3600 * 24);
    return dayDifference + 1; // return 몇일
}


function getAllDatesBetween(startDate, endDate) {
    const dateObj = {};
    const currentDate = new Date(startDate);
    const maxDate = new Date(endDate);

    while (currentDate <= maxDate) {
        const currentStr = convertDateFormat(currentDate.toISOString().split('T')[0], 'YYYY-MM-DD') ;
        dateObj[currentStr] = []; // 리스트로 일정에 대해 초기화 한다.
        currentDate.setDate(currentDate.getDate() + 1); // 다음날 추가
    }

    console.log(dateObj);

    return dateObj;
}

export {
    convertDateFormat,
    calculateDaysBetweenDates,
    getAllDatesBetween
};