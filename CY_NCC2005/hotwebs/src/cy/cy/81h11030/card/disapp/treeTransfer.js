import React, { Component } from 'react';
import { base } from 'nc-lightapp-front';
let { NCButton, NCFormControl, NCDiv} = base;
import './treeTransfer.less';

class Treetransfer extends Component {
    constructor(props) {
        super(props);
        this.props = props;
        this.state = {
            leftSearchValue: '',
            rightSearchValue: '',
            disableBtns: this.props.disableBtns,
            hiddenAllMoveBtns: this.props.hiddenAllMoveBtns       
        }
    }

    
    onLeftSearch = (e) => {
        let trimStr = e.replace(/^\s+|\s+$/g, '');
        this.props.leftSearch(trimStr);
    }

    onLeftChange = (e) => {
        let trimStr = e.replace(/^\s+|\s+$/g, '');
        if(trimStr==''){
            this.props.leftSearch(trimStr);
        }
        this.setState({leftSearchValue: e});

        if(this.props.autoSearch){
            this.onLeftSearch(e);
        }
    }

    leftClearSearch = () => {
        this.onLeftChange('');
    }

    onRightSearch = (e) => {
        // console.log('rightSearch',e);
        let trimStr = e.replace(/^\s+|\s+$/g, '');
        this.props.rightSearch(trimStr);
    }
    onRightChange = (e)=> {
        let trimStr = e.replace(/^\s+|\s+$/g, '');
        if(trimStr == ''){
            this.props.rightSearch(trimStr);
        }
        this.setState({rightSearchValue: e});

        if(this.props.autoSearch){
            this.onRightSearch(e);
        }
    }
    rightClearSearch = () => {
        this.onRightChange('');
    }
    componentWillReceiveProps(nextProps){
        this.setState({
            disableBtns: nextProps.disableBtns,
            hiddenAllMoveBtns: nextProps.hiddenAllMoveBtns
        });
    }
    
    render(){
        return (
            <NCDiv fieldid="transfer" className = {this.props.fullscreen ? 'transfer-main fullscreen-transfer-main':'transfer-main'} areaCode={NCDiv.config.Area}>
                <div className='left-wrapper nc-theme-area-split-bc nc-theme-transfer-wrap-bgc'>
                    <div className='content-box nc-theme-area-split-bc nc-theme-transfer-wrap-bgc'>
                        <h2 className="nc-theme-transfer-list-header-bgc nc-theme-transfer-list-header-c">{this.props.title && this.props.title.left ? this.props.title.left : ''}</h2>
                        <NCDiv fieldid="left" areaCode={NCDiv.config.TREE} className = {this.props.fullscreen ? 'left-area fullscreen-left-area nc-theme-transfer-list-body-bgc':'left-area nc-theme-transfer-list-body-bgc'}>
                            <div className="search-content">
                            {
                                this.props.showSearch ? 
                                    <NCFormControl
                                        fieldid="search"
                                        type="search"
                                        placeholder={this.props.searchPlaceholder}
                                        value={this.state.leftSearchValue}
                                        onChange={this.onLeftChange}
                                        onSearch={this.onLeftSearch}
                                        clearSearch={this.leftClearSearch}
                                    />: ''
                            }
                            </div>
                            <NCDiv className="tree-box" fieldid="left" areaCode={NCDiv.config.TreeCom}>
                                {this.props.leftArea()}
                            </NCDiv>
                        </NCDiv>
                    </div>
                </div>
                <div className="right-wrapper nc-theme-transfer-wrap-bgc">
                    <div className="content-box nc-theme-area-split-bc nc-theme-transfer-wrap-bgc">
                        <h2 className="nc-theme-transfer-list-header-bgc nc-theme-transfer-list-header-c">{this.props.title && this.props.title.right ? this.props.title.right : ''}</h2>
                        <NCDiv fieldid="right" areaCode={NCDiv.config.TREE} className = {this.props.fullscreen ? 'right-area fullscreen-right-area nc-theme-transfer-list-body-bgc':'right-area nc-theme-transfer-list-body-bgc'}>
                            <div className="search-content">
                                {
                                    this.props.showSearch ?
                                        <NCFormControl
                                            fieldid="search"
                                            type="search"
                                            placeholder={this.props.searchPlaceholder}
                                            value={this.state.rightSearchValue}
                                            onChange={this.onRightChange}
                                            clearSearch={this.rightClearSearch}
                                            onSearch={this.onRightSearch}
                                        /> : ''
                                }
                            </div>
                            <NCDiv className="tree-box" fieldid="right" areaCode={NCDiv.config.TreeCom}>
                                {this.props.rightArea()}
                            </NCDiv>
                        </NCDiv>
                    </div>
                </div>
                <div className = 'button-area'>
                    <div className="opr-botton">
                        <NCButton fieldid="right" className="nc-theme-btn-secondary" onClick= {this.props.toRight} disabled={!this.state.disableBtns ? false : true}>&gt;</NCButton>
                    </div>
                    {
                        !this.state.hiddenAllMoveBtns ? 
                        <div>
                            <div className= "opr-botton">
                                <NCButton fieldid="alltoright" className="nc-theme-btn-secondary" onClick={this.props.allToRight} disabled={!this.state.disableBtns ? false : true}>&gt;&gt;</NCButton>
                            </div>
                            <div className= "opr-botton">
                                <NCButton fieldid="alltoleft" className="nc-theme-btn-secondary" onClick={this.props.allToLeft} disabled = {this.props.rightFixed && !this.props.allToLeft_without_merge || this.state.disableBtns ? true: false}>&lt;&lt;</NCButton>
                                {/* <NCButton onClick={this.props.allToLeft} disabled = {!this.state.disableBtns ? false : true}>&lt;&lt;</NCButton> */}
                            </div> 
                        </div> : ''
                    }
                    
                    <div className="opr-botton">
                        <NCButton fieldid="left" className="nc-theme-btn-secondary" onClick= {this.props.toLeft} disabled={!this.state.disableBtns ? false : true}>&lt;</NCButton>
                    </div>
                </div>
            </NCDiv>
        )
    }
}

Treetransfer.defaultProps = {
    showSearch: true,
    searchPlaceholder: "",
    autoSearch: false
}
export default Treetransfer;