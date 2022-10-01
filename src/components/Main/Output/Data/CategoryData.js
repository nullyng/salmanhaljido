function ShowData(dic, swap) {
    if (Object.keys(dic).length === 0) {
        return ("받아온 정보가 없어요.")
    }
    else {
        const result = []
        for (var i in dic) {
            result.push(<p> {swap[i]}은(는) {dic[i]} 개가 있어요. </p>)
        }
        return result
    }
}


function CategoryData ({ statistics }) {
    const swap = {
        academy:"학원 및 교습소", 
        animalHospital:"동물병원", 
        animalBeauty:"동물미용", 
        carAccident:"교통사고", 
        childSafety:"어린이보호구역", 
        concertHall:"공연장", 
        crime:"범죄", 
        drugStore:"약국", 
        electricVehicleCharging:"전기차 충전소", 
        entertainment:"미술관, 박물관", 
        facilitiesForTheDisabled:"장애인 편의시설", 
        femaleSafety:"여성안전", 
        hospital:"의료기관", 
        jeonse:"전세", 
        kindergarden:"유치원", 
        library:"도서관", 
        mart:"마트, 시장", 
        park:"공원", 
        parkinglot:"공영, 민영주차장", 
        publicTransportationUtilizationRate:"대중교통 이용률", 
        restaurant:"음식점", 
        school:"초, 중, 고등학교", 
        shelter:"민방위 대피소", 
        sportsFacilities:"스포츠시설", 
        station:"대중교통 정류장", 
        theater:"영화관", 
        trading:"매매",
      }
    
      const dummystatistics = {
        academy:15, 
        animalHospital:1, 
        animalBeauty:1, 
        childSafety:6, 
        drugStore:5, 
        electricVehicleCharging:1, 
        hospital:3, 
        kindergarden:1, 
        mart:13, 
        park:7, 
        restaurant:220, 
        school:34, 
      }

    return(
        <div>
            {/* 나중에 실제 데이터 넘어오면 바꿔주면 됩니다 */}
            {/* <p>{statistics}</p> */}
            {ShowData(dummystatistics, swap)}
        </div>
    )
}
export default CategoryData;