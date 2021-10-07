/*cI4u54VYZVPxnvGrX5EL6KE+e4UHx5s0LTDNIZXK368BgHLAtPbf1nut6ms7Loii*/
import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { createPage, base, ajax, high } from 'nc-lightapp-front';
import Transfer from './Transfer'
const { NCIcon, NCFormControl, NCButton, NCTabs, NCCreateSearch, NCRadio,NCMessage  } = base;
const NCTabPane = NCTabs.NCTabPane;

class Disapp extends Component {
	constructor(props) {
		super(props);	  
		this.state={
			firstStepOrgValue:{
				leftTreeData:[],
				rightTreeData:[],				
			},
			isLoaded:false,
			selectedValue:'onlyChild',
			onlySelf:false,
			json:{},
            inlt:null
        }
	}
	componentWillMount() {
        let callback = (json, status, inlt) => { // json 多语json格式参数； status： 是否请求到json多语，可用来判断多语资源是否请求到并进行一些别的操作； inlt： 可用来进行占位符的一些操作
            if (status) {
                this.setState({ json, inlt });// 保存json和inlt到页面state中并刷新页面
            } else {
                console.log(未加载到多语资源);   // 未请求到多语资源的后续操作/* 国际化处理： 未加载到多语资源,未加载到多语资源*/
            }
        }
        this.props.MultiInit.getMultiLang({ 'moduleId': 'resp-001', 'domainName': 'uap', callback });

		
		


    }
	componentDidMount() {
		this.getData();
		
	}
	getData = () => {
		let data = { respid:  this.props.respid,headcyclass: this.props.headcyclass}
		let that = this;
		ajax({
			data:data,
			url: '/nccloud/cy/cy/querystuclatree.do',
			success: function (res) {
				debugger;
				let { success, data } = res;
				if (success) {
					debugger;
					that.setState({
						firstStepOrgValue : {
					        leftTreeData : data.leftdata?data.leftdata:[],
					        rightTreeData : data.rightdata?data.rightdata:[],
						},
						isLoaded: true
					},

					
					
					function() {
						that.props.getdisapp(that.state.firstStepOrgValue.rightTreeData);				
					});	
				}
			}
		})
	}
	render() {
		return (
			<div>
				<div>
					{this.state.isLoaded ? <Transfer 
					TransferId={'disapp'} 
					title={{left: '待选择的学生', right: '已选择的学生'}}/* 国际化处理： 待分配应用,已分配应用*/
					leftTreeData={this.state.firstStepOrgValue.leftTreeData} 
					rightTreeData={this.state.firstStepOrgValue.rightTreeData} 
					value={this.state.firstStepOrgValue}
                    />: ''}
				</div>
			</div> 
		);
	}
}

export default Disapp = createPage({

})(Disapp);

/*cI4u54VYZVPxnvGrX5EL6KE+e4UHx5s0LTDNIZXK368BgHLAtPbf1nut6ms7Loii*/