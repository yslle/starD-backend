import {Link, Route, Router, useParams, useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import searchicon from "./images/search.png";
import axios from "axios";


const SearchBar = (props) => {

	const items = props.searchItems;
	const [search, setSearch] = useState("");
	const [selectOption, setSelectOption] = useState("제목");
	const navigate = useNavigate();


	//엔터를 치면 해당 검색페이지로 넘어갑니다
	const handleKeyDown = (e) => {
		// 엔터 키의 키 코드는 13입니다.
		if (e.keyCode === 13) {
			// 엔터 키를 눌렀을 때 할 작업을 여기에 추가합니다.
			console.log("엔터 키를 눌렀습니다.");
			// 예시: 입력한 내용을 상태에 저장하고 폼을 제출합니다.
			setSearch(e.target.value);
			searchItem(e.target.value);
		}
	};
	const onChange=(e)=>{
		console.log("Search", e.target.value);
		setSearch(e.target.value)
	}

	const onHandleselect = (e)=>{
		setSelectOption(e.target.value);
		console.log(`value = ${e.target.value}`)
	}

	//url 동적으로 바꾸기 선택한 옵션과 검색내용이 url안으로 들어갑니다
	const searchItem = (item)=>{
		console.log("타깃",item)
		setSearch(item);
		const queryParams = `?q=${encodeURIComponent(item)}&select=${encodeURIComponent(selectOption)}`;
		navigate(`/search${queryParams}`);
	}

	return (
		<div className="Home_wrap">
			<div className="select_search">
				<select id="sub" value={selectOption} onChange={onHandleselect}>
					<option value="제목">제목</option>
					<option value="내용">내용</option>
					<option value="작성자">작성자</option>
				</select>
			</div>

			<div className="searchbar">
				<div className="searchinput">
					<input
						type="text"
						value={search}
						onChange={onChange}
						onKeyDown={handleKeyDown}
						placeholder={"원하는 스터디를 검색해보세요"}
					/>
					<img src ={searchicon} width="20px"/>
				</div>
			</div>
		</div>
	);
};

export default SearchBar;
