//페이징
import {useEffect, useState} from "react";
// import Pagination from "./Pagination";
import Pagination from "react-js-pagination";
const Paging = ({page,totalItemCount,itemsPerPage, handlePageChange }) => {
    useEffect(() => {
        console.log("pagessss:",page);
    }, []);
     const [currentpage, setCurrentPage] = useState(page);
    const handleLocalPageChange = (selectedPage) => {
        handlePageChange(selectedPage);
    };

    return (
        <Pagination
            activePage={page} // 현재 페이지
            itemsCountPerPage={itemsPerPage} // 한 페이지랑 보여줄 아이템 갯수
            totalItemsCount={totalItemCount} // 총 아이템 갯수
            pageRangeDisplayed={10} // paginator의 페이지 범위
            prevPageText={"‹"} // "이전"을 나타낼 텍스트
            nextPageText={"›"} // "다음"을 나타낼 텍스트
            onChange={handleLocalPageChange}/> // 페이지 변경을 핸들링하는 함수
    );
};
export default Paging;