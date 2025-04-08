/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 91.32631578947368, "KoPercent": 8.673684210526316};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.8797368421052632, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "https://cdn.bikedekho.com/pwa/css/consent.2ca38cd7e3bd80f142f136cf7de8e4e1.css"], "isController": false}, {"data": [0.738, 500, 1500, "https://bd.gaadicdn.com/pwa/js/central-lf/bundle.js?_v=0704202510_143.5_79.1"], "isController": false}, {"data": [0.904, 500, 1500, "https://www.googletagmanager.com/gtm.js?id=GTM-TFCXWN9"], "isController": false}, {"data": [0.81, 500, 1500, "https://www.zigcdn.com/js/thirdparty.js"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/revamp/sprites.png"], "isController": false}, {"data": [1.0, 500, 1500, "https://cdn.bikedekho.com/pwa/css/pagination.33e806fbbd88b8a310d90afc78678daf.css"], "isController": false}, {"data": [0.996, 500, 1500, "https://images.zigcdn.com/images/revamp/zigwheels-logo-black.svg"], "isController": false}, {"data": [1.0, 500, 1500, "https://media.zigcdn.com/media/model/2025/Jan/suzuki-access-125std-right-side-view_360x240.png"], "isController": false}, {"data": [0.382, 500, 1500, "https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=zw_jquery.min,one_javascript,zw_jqueryui_autocomplete.min,zw_bootstrap.min,rvmp_zw_javascript,rvmp_similar_cars_slider,rvmp_central_cta&version=143.5"], "isController": false}, {"data": [0.999, 500, 1500, "https://media.zigcdn.com/media/model/2024/Apr/yamaha-mt15-v2-right-side-view_360x240.jpg"], "isController": false}, {"data": [0.999, 500, 1500, "https://media.zigcdn.com/media/model/2025/Jan/ktm-390-smc-r-right-side-view_360x240.png"], "isController": false}, {"data": [0.982, 500, 1500, "https://www.googletagmanager.com/gtag/js?id=G-NNM7DKZYPC&l=dataLayer&cx=c&gtm=45He5421h1v71748709za200&tag_exp=102015666~102788824~102803279~102813109~102887800~102926062~102975949~103016951~103021830~103027016"], "isController": false}, {"data": [1.0, 500, 1500, "https://cdn.bikedekho.com/pwa/css/SocialBox.64b2b9c3ef2eb01f0648af0ad3ea7c6a.css"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/launcher-icon-144.png"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/revamp/logo/cross-circle-outlined.svg"], "isController": false}, {"data": [1.0, 500, 1500, "https://cdn.bikedekho.com/pwa/css/socialsharepopup.3473928aa58b747b3e5e052c3c90de11.css"], "isController": false}, {"data": [0.892, 500, 1500, "https://accounts.google.com/gsi/client"], "isController": false}, {"data": [0.972, 500, 1500, "https://www.googletagmanager.com/gtag/js?id=G-7E99GHYDBR&l=dataLayer&cx=c&gtm=45He5421v79891213za200&tag_exp=102788824~102803279~102813109~102887799~102926062~102975949~103016951~103021830~103027016"], "isController": false}, {"data": [0.999, 500, 1500, "https://media.zigcdn.com/media/model/2025/Jan/ktm-390-enduro-r-right-side-view_360x240.png"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/revamp/home_left.png"], "isController": false}, {"data": [0.187, 500, 1500, "https://www.zigwheels.com/upcoming-bikes"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/revamp/w-carbike.svg"], "isController": false}, {"data": [0.999, 500, 1500, "https://media.zigcdn.com/media/model/2025/Jan/vida-z-abc-right-side-view_360x240.jpg"], "isController": false}, {"data": [0.996, 500, 1500, "https://cdn.bikedekho.com/pwa/js/central-lf/cities.be51a5394817f3ccebd9.js"], "isController": false}, {"data": [0.994, 500, 1500, "https://images.zigcdn.com/images/revamp/site_sprite.png"], "isController": false}, {"data": [0.396, 500, 1500, "https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=rvmp_helpful_review,rvmp_ad_commonscript&version=143.5"], "isController": false}, {"data": [0.376, 500, 1500, "https://www.zigcdn.com/css/css_bundler_new.html?type=css&files=rvmp_make,rvmp_new_car,rvmp_brand,rvmp_bodytypes_rhs&version=79.1"], "isController": false}, {"data": [0.999, 500, 1500, "https://media.zigcdn.com/media/model/2023/Mar/continental-dux-dulux-right-side-view_360x240.jpg"], "isController": false}, {"data": [0.798, 500, 1500, "https://www.zigcdn.com/css/new-fonts/Roboto-Regular.woff2"], "isController": false}, {"data": [1.0, 500, 1500, "https://media.zigcdn.com/media/model/2022/Nov/eko-tejas-dryoth-right-side-view_360x240.jpg"], "isController": false}, {"data": [1.0, 500, 1500, "https://cdn.bikedekho.com/pwa/css/crossSellCarousal.c5755b0daec1e7095473588092bb4853.css"], "isController": false}, {"data": [0.752, 500, 1500, "https://www.zigcdn.com/css/new-fonts/Roboto-Medium.woff2"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/revamp/logo/app_header_banner.png"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/revamp/ev_icn.svg"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/revamp/make_new.png"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/place_holder_image.png"], "isController": false}, {"data": [1.0, 500, 1500, "https://cdn.bikedekho.com/pwa/css/DealerWidget.3fd95a64dfeee2e87139e702f7eacc24.css"], "isController": false}, {"data": [0.75, 500, 1500, "https://www.zigcdn.com/css/new-fonts/zw09.woff2?m0gxc2"], "isController": false}, {"data": [1.0, 500, 1500, "https://media.zigcdn.com/media/model/2024/Dec/triumph-scrambler-400-right-side-view_360x240.jpg"], "isController": false}, {"data": [1.0, 500, 1500, "https://media.zigcdn.com/media/model/2022/Aug/re-hunter-350-mn-right-side-view_360x240.jpg"], "isController": false}, {"data": [0.416, 500, 1500, "https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=rvmp_make,rvmp_quick_search_mmv,rvmp_n_gpopup,rvmp_rhs_slider&version=143.5"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/revamp/header_spirit_new.png"], "isController": false}, {"data": [0.997, 500, 1500, "https://media.zigcdn.com/media/model/2025/Feb/bajaj-chetak-3503-right-side-view_360x240.jpg"], "isController": false}, {"data": [0.38, 500, 1500, "https://www.zigcdn.com/css/css_bundler_new.html?type=css&files=rvmp_bootstrap_small.min,rvmp_zw_style_small,rvmp_rhs,onejs,rvmp_community_exit,rvmp_bottom_nav_style,rvmp_icon_zw_style&version=79.1"], "isController": false}, {"data": [0.485, 500, 1500, "https://www.zigwheels.com/manifest.json"], "isController": false}, {"data": [1.0, 500, 1500, "https://media.zigcdn.com/media/model/2024/Sep/royal-enfield-classic-350-right-side-view_360x240.jpg"], "isController": false}, {"data": [1.0, 500, 1500, "https://cdn.bikedekho.com/pwa/css/whatsappConnect.21e146783aa9a2abb9b6886270ec3ec4.css"], "isController": false}, {"data": [0.976, 500, 1500, "https://www.googletagmanager.com/gtm.js?id=GTM-MCTS4Z"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/revamp/home_right.png"], "isController": false}, {"data": [0.302, 500, 1500, "https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=rvmp_myaccount,rvmp_one_tap_login&version=143.5"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/revamp/body-types.png"], "isController": false}, {"data": [1.0, 500, 1500, "https://cdn.bikedekho.com/pwa/css/breadcrumb.86a0a80c28c772f610ef3c9590178a2e.css"], "isController": false}, {"data": [0.934, 500, 1500, "https://bd.gaadicdn.com/pwa/css/central-lf.min.css?_v=0704202510_143.5_79.1"], "isController": false}, {"data": [1.0, 500, 1500, "https://images.zigcdn.com/images/spacer.png"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 19000, 1648, 8.673684210526316, 146.5120526315792, 20, 1737, 88.0, 295.0, 548.9500000000007, 942.9900000000016, 263.5046113306983, 14187.375723770889, 117.45525730705221], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["https://cdn.bikedekho.com/pwa/css/consent.2ca38cd7e3bd80f142f136cf7de8e4e1.css", 250, 0, 0.0, 122.56000000000012, 26, 455, 98.0, 237.20000000000005, 297.69999999999993, 413.1700000000003, 4.136881122584061, 3.947004742934207, 1.7452467235901508], "isController": false}, {"data": ["https://bd.gaadicdn.com/pwa/js/central-lf/bundle.js?_v=0704202510_143.5_79.1", 250, 0, 0.0, 577.472, 97, 1698, 523.0, 1122.0, 1259.35, 1548.2600000000007, 4.077239219779503, 3237.2682153210417, 1.7121219379933459], "isController": false}, {"data": ["https://www.googletagmanager.com/gtm.js?id=GTM-TFCXWN9", 250, 0, 0.0, 288.79599999999965, 79, 988, 201.5, 604.8, 809.4999999999997, 978.45, 4.084700346382589, 1006.8100092263169, 1.627497794261813], "isController": false}, {"data": ["https://www.zigcdn.com/js/thirdparty.js", 250, 0, 0.0, 294.0040000000002, 43, 1044, 187.0, 583.3000000000001, 639.3499999999999, 740.47, 4.232840066370932, 152.26504563001592, 1.624517720784938], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/sprites.png", 500, 0, 0.0, 103.53399999999998, 31, 445, 94.0, 171.90000000000003, 197.89999999999998, 238.95000000000005, 8.182636445462727, 178.10818723917848, 3.507985741755994], "isController": false}, {"data": ["https://cdn.bikedekho.com/pwa/css/pagination.33e806fbbd88b8a310d90afc78678daf.css", 250, 0, 0.0, 75.63199999999999, 28, 241, 65.5, 118.0, 148.0, 200.49, 4.165972337943676, 12.453165357857024, 1.7697245771538077], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/zigwheels-logo-black.svg", 250, 0, 0.0, 101.76400000000001, 36, 705, 88.5, 162.8, 179.0, 453.86000000000195, 4.11502312642997, 102.21251291088505, 1.679765299655984], "isController": false}, {"data": ["https://media.zigcdn.com/media/model/2025/Jan/suzuki-access-125std-right-side-view_360x240.png", 500, 0, 0.0, 85.95000000000003, 26, 243, 77.0, 141.0, 167.79999999999995, 213.8800000000001, 8.146772248835012, 72.23497913407958, 3.8585786530126764], "isController": false}, {"data": ["https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=zw_jquery.min,one_javascript,zw_jqueryui_autocomplete.min,zw_bootstrap.min,rvmp_zw_javascript,rvmp_similar_cars_slider,rvmp_central_cta&version=143.5", 250, 120, 48.0, 378.11600000000004, 23, 1309, 185.0, 1052.5, 1087.8, 1168.8000000000002, 4.052192235999676, 779.281283557217, 2.3070586656130967], "isController": false}, {"data": ["https://media.zigcdn.com/media/model/2024/Apr/yamaha-mt15-v2-right-side-view_360x240.jpg", 500, 0, 0.0, 83.53399999999995, 26, 817, 73.0, 139.90000000000003, 157.0, 199.8900000000001, 8.145975887911373, 75.56506343678723, 3.810471142880417], "isController": false}, {"data": ["https://media.zigcdn.com/media/model/2025/Jan/ktm-390-smc-r-right-side-view_360x240.png", 500, 0, 0.0, 93.27599999999998, 33, 617, 79.0, 161.7000000000001, 182.84999999999997, 231.8900000000001, 8.170335147147735, 102.40046999852933, 3.813886914391228], "isController": false}, {"data": ["https://www.googletagmanager.com/gtag/js?id=G-NNM7DKZYPC&l=dataLayer&cx=c&gtm=45He5421h1v71748709za200&tag_exp=102015666~102788824~102803279~102813109~102887800~102926062~102975949~103016951~103021830~103027016", 250, 0, 0.0, 222.48399999999992, 80, 833, 193.0, 374.0, 468.7999999999997, 684.7000000000012, 4.245491288251877, 1820.266181026687, 2.4129647751587813], "isController": false}, {"data": ["https://cdn.bikedekho.com/pwa/css/SocialBox.64b2b9c3ef2eb01f0648af0ad3ea7c6a.css", 250, 0, 0.0, 77.688, 25, 298, 67.0, 137.70000000000002, 168.4999999999999, 218.64000000000033, 4.168681529405879, 7.726716350402695, 1.7668044763302264], "isController": false}, {"data": ["https://images.zigcdn.com/images/launcher-icon-144.png", 500, 0, 0.0, 82.72999999999998, 24, 244, 71.0, 143.0, 165.89999999999998, 215.96000000000004, 8.4960323528912, 30.926719331447213, 3.692123434606039], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/logo/cross-circle-outlined.svg", 250, 0, 0.0, 73.80000000000004, 28, 235, 62.0, 130.9, 146.89999999999998, 197.0700000000004, 4.1152940789148795, 4.987382765559927, 1.703988954550692], "isController": false}, {"data": ["https://cdn.bikedekho.com/pwa/css/socialsharepopup.3473928aa58b747b3e5e052c3c90de11.css", 250, 0, 0.0, 74.28, 25, 234, 63.5, 130.0, 150.0, 198.82000000000016, 4.169376761561682, 6.909601918330248, 1.7956007342272478], "isController": false}, {"data": ["https://accounts.google.com/gsi/client", 250, 0, 0.0, 335.12400000000014, 80, 1306, 219.0, 726.0, 906.9, 1199.2100000000003, 4.188867665292718, 960.047660150883, 1.6035509031198685], "isController": false}, {"data": ["https://www.googletagmanager.com/gtag/js?id=G-7E99GHYDBR&l=dataLayer&cx=c&gtm=45He5421v79891213za200&tag_exp=102788824~102803279~102813109~102887799~102926062~102975949~103016951~103021830~103027016", 250, 0, 0.0, 247.04400000000018, 86, 941, 215.5, 423.8, 534.9499999999998, 868.6600000000003, 4.085100820288245, 1588.8473205057762, 2.2659543612536357], "isController": false}, {"data": ["https://media.zigcdn.com/media/model/2025/Jan/ktm-390-enduro-r-right-side-view_360x240.png", 500, 0, 0.0, 103.32600000000005, 30, 542, 88.0, 171.90000000000003, 206.79999999999995, 319.95000000000005, 8.214631902344456, 99.83104170773983, 3.858630805691097], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/home_left.png", 250, 0, 0.0, 77.52000000000005, 23, 391, 66.0, 132.60000000000002, 170.0999999999998, 241.0, 4.10664126024607, 3.585290319003893, 0.6296315213463213], "isController": false}, {"data": ["https://www.zigwheels.com/upcoming-bikes", 500, 360, 72.0, 274.0639999999999, 26, 1541, 103.0, 947.4000000000002, 1024.8, 1369.8600000000001, 7.760119195430842, 469.54838288816114, 4.925705346334119], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/w-carbike.svg", 500, 0, 0.0, 79.55199999999995, 26, 337, 71.0, 138.90000000000003, 162.0, 201.97000000000003, 8.20223428862022, 21.64153109056907, 3.960942241916698], "isController": false}, {"data": ["https://media.zigcdn.com/media/model/2025/Jan/vida-z-abc-right-side-view_360x240.jpg", 500, 0, 0.0, 88.19999999999997, 28, 537, 76.0, 147.90000000000003, 173.0, 214.99, 8.152749922548875, 73.97585537633094, 3.78179317696359], "isController": false}, {"data": ["https://cdn.bikedekho.com/pwa/js/central-lf/cities.be51a5394817f3ccebd9.js", 250, 0, 0.0, 171.76000000000008, 54, 572, 155.0, 281.9, 323.84999999999985, 492.5300000000009, 4.167291760430731, 464.3233919983831, 1.7417977279925323], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/site_sprite.png", 500, 0, 0.0, 100.23999999999994, 32, 796, 86.0, 162.0, 189.79999999999995, 516.0, 8.197662026789958, 123.59736721836931, 3.570466078074533], "isController": false}, {"data": ["https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=rvmp_helpful_review,rvmp_ad_commonscript&version=143.5", 250, 148, 59.2, 158.488, 27, 629, 84.5, 418.70000000000005, 457.74999999999983, 598.6600000000003, 4.126638275395332, 20.09047396503912, 1.9263018512099304], "isController": false}, {"data": ["https://www.zigcdn.com/css/css_bundler_new.html?type=css&files=rvmp_make,rvmp_new_car,rvmp_brand,rvmp_bodytypes_rhs&version=79.1", 500, 237, 47.4, 278.31999999999965, 33, 1070, 117.0, 746.5000000000002, 798.9, 953.6600000000003, 8.017574523355194, 154.01361346571684, 4.034625148325128], "isController": false}, {"data": ["https://media.zigcdn.com/media/model/2023/Mar/continental-dux-dulux-right-side-view_360x240.jpg", 500, 0, 0.0, 82.812, 32, 669, 72.0, 134.80000000000007, 158.84999999999997, 212.0, 8.146772248835012, 69.56579740606772, 3.866534485286929], "isController": false}, {"data": ["https://www.zigcdn.com/css/new-fonts/Roboto-Regular.woff2", 250, 0, 0.0, 301.6320000000002, 33, 1256, 186.5, 580.9, 638.8, 844.1600000000017, 4.056860963261067, 75.91439110573802, 1.7669531148578475], "isController": false}, {"data": ["https://media.zigcdn.com/media/model/2022/Nov/eko-tejas-dryoth-right-side-view_360x240.jpg", 500, 0, 0.0, 86.34, 28, 217, 76.0, 149.90000000000003, 164.0, 192.99, 8.152616990053808, 81.98793922224034, 3.829500754117072], "isController": false}, {"data": ["https://cdn.bikedekho.com/pwa/css/crossSellCarousal.c5755b0daec1e7095473588092bb4853.css", 250, 0, 0.0, 80.25999999999999, 29, 243, 70.5, 136.8, 161.79999999999995, 201.43000000000006, 4.170628763992459, 17.647787533573563, 1.8002128063326828], "isController": false}, {"data": ["https://www.zigcdn.com/css/new-fonts/Roboto-Medium.woff2", 250, 0, 0.0, 342.2400000000003, 31, 1113, 496.5, 637.9, 668.3499999999999, 730.6600000000003, 4.057716966126178, 77.46817291656522, 1.7633633300060054], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/logo/app_header_banner.png", 250, 0, 0.0, 106.31999999999996, 29, 396, 89.0, 181.0, 226.34999999999997, 364.99000000000046, 4.1046859094342105, 6.012723500147769, 1.6835625800413752], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/ev_icn.svg", 250, 0, 0.0, 82.028, 24, 217, 73.5, 145.0, 165.89999999999998, 212.47000000000003, 4.109814236396515, 5.28978043317442, 0.6180775316455697], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/make_new.png", 500, 0, 0.0, 85.07199999999992, 28, 237, 73.0, 145.90000000000003, 177.95, 203.98000000000002, 8.219088009994412, 32.001468648288785, 3.5316393792944734], "isController": false}, {"data": ["https://images.zigcdn.com/images/place_holder_image.png", 500, 0, 0.0, 82.90400000000007, 27, 389, 68.0, 145.90000000000003, 185.69999999999993, 225.98000000000002, 8.232349841938882, 14.233765034328899, 3.5855742475632244], "isController": false}, {"data": ["https://cdn.bikedekho.com/pwa/css/DealerWidget.3fd95a64dfeee2e87139e702f7eacc24.css", 250, 0, 0.0, 81.69200000000002, 30, 453, 64.0, 139.9, 161.89999999999998, 450.49, 4.171255047218607, 22.265703211032136, 1.780115679330597], "isController": false}, {"data": ["https://www.zigcdn.com/css/new-fonts/zw09.woff2?m0gxc2", 250, 0, 0.0, 370.3239999999999, 31, 1349, 500.5, 651.9, 708.0, 841.0900000000004, 4.100579001755047, 77.40518820632474, 1.777985426542228], "isController": false}, {"data": ["https://media.zigcdn.com/media/model/2024/Dec/triumph-scrambler-400-right-side-view_360x240.jpg", 500, 0, 0.0, 80.74599999999997, 30, 239, 70.0, 141.0, 159.95, 222.83000000000015, 8.14637404891083, 52.720723459113344, 3.8663454958697883], "isController": false}, {"data": ["https://media.zigcdn.com/media/model/2022/Aug/re-hunter-350-mn-right-side-view_360x240.jpg", 500, 0, 0.0, 83.27199999999999, 30, 287, 72.0, 147.0, 162.0, 204.97000000000003, 8.143189850328172, 61.41985039942346, 3.825072576179541], "isController": false}, {"data": ["https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=rvmp_make,rvmp_quick_search_mmv,rvmp_n_gpopup,rvmp_rhs_slider&version=143.5", 500, 263, 52.6, 176.11199999999988, 30, 1532, 100.0, 564.7, 701.8, 855.8200000000002, 8.170602173380177, 150.4667775655691, 4.161900482065528], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/header_spirit_new.png", 250, 0, 0.0, 87.03999999999994, 29, 362, 73.5, 165.60000000000002, 187.34999999999997, 222.0, 4.119837843182492, 20.398025258725816, 1.6696608446491545], "isController": false}, {"data": ["https://media.zigcdn.com/media/model/2025/Feb/bajaj-chetak-3503-right-side-view_360x240.jpg", 500, 0, 0.0, 95.18200000000002, 32, 857, 82.0, 157.90000000000003, 184.95, 257.97, 8.149560738676184, 147.67099561146154, 3.8360237070721888], "isController": false}, {"data": ["https://www.zigcdn.com/css/css_bundler_new.html?type=css&files=rvmp_bootstrap_small.min,rvmp_zw_style_small,rvmp_rhs,onejs,rvmp_community_exit,rvmp_bottom_nav_style,rvmp_icon_zw_style&version=79.1", 250, 115, 46.0, 371.39600000000024, 28, 1737, 178.0, 881.0, 977.05, 1263.3700000000028, 3.965610228101901, 347.51040538946256, 2.1764384259699883], "isController": false}, {"data": ["https://www.zigwheels.com/manifest.json", 500, 252, 50.4, 189.9540000000001, 27, 708, 162.0, 385.90000000000003, 431.95, 613.98, 8.149826408697495, 11.152639499559909, 3.5723936345780833], "isController": false}, {"data": ["https://media.zigcdn.com/media/model/2024/Sep/royal-enfield-classic-350-right-side-view_360x240.jpg", 500, 0, 0.0, 83.81400000000006, 30, 287, 74.0, 145.80000000000007, 160.0, 216.95000000000005, 8.144250973237991, 62.473722065219164, 3.8971513446158355], "isController": false}, {"data": ["https://cdn.bikedekho.com/pwa/css/whatsappConnect.21e146783aa9a2abb9b6886270ec3ec4.css", 250, 0, 0.0, 78.816, 29, 208, 71.0, 134.50000000000003, 153.45, 178.49, 4.171742286448512, 14.100000052146779, 1.7925455137083453], "isController": false}, {"data": ["https://www.googletagmanager.com/gtm.js?id=GTM-MCTS4Z", 250, 0, 0.0, 243.868, 91, 1287, 203.0, 407.8, 501.39999999999986, 965.4300000000019, 4.180881664325374, 1902.2346714506405, 1.661737145879323], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/home_right.png", 250, 0, 0.0, 88.41200000000005, 20, 424, 69.5, 167.0, 202.0, 380.67000000000075, 4.10711352061771, 3.537572387875801, 0.6337147815015607], "isController": false}, {"data": ["https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=rvmp_myaccount,rvmp_one_tap_login&version=143.5", 250, 153, 61.2, 171.51600000000005, 23, 693, 85.0, 532.8, 572.9, 682.4100000000001, 4.189008042895442, 34.13742106337969, 1.926780066605228], "isController": false}, {"data": ["https://images.zigcdn.com/images/revamp/body-types.png", 500, 0, 0.0, 83.968, 27, 242, 70.0, 153.0, 174.0, 220.84000000000015, 8.190544835042427, 38.46116683525538, 3.5353718916882353], "isController": false}, {"data": ["https://cdn.bikedekho.com/pwa/css/breadcrumb.86a0a80c28c772f610ef3c9590178a2e.css", 250, 0, 0.0, 79.79199999999993, 25, 318, 66.5, 142.70000000000002, 169.24999999999994, 237.98000000000002, 4.161326297917673, 7.001919151672021, 1.7677509175724488], "isController": false}, {"data": ["https://bd.gaadicdn.com/pwa/css/central-lf.min.css?_v=0704202510_143.5_79.1", 250, 0, 0.0, 290.70800000000014, 62, 1309, 212.0, 558.9, 700.4999999999997, 1223.3200000000015, 4.163058682475188, 1947.160930578915, 1.744093920685406], "isController": false}, {"data": ["https://images.zigcdn.com/images/spacer.png", 250, 0, 0.0, 76.53600000000002, 26, 394, 63.0, 137.70000000000002, 159.0, 221.41000000000008, 4.114210483008311, 3.1539601847280507, 1.595060118900683], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["400/Bad Request", 250, 15.169902912621358, 1.3157894736842106], "isController": false}, {"data": ["403/Forbidden", 1398, 84.83009708737865, 7.3578947368421055], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 19000, 1648, "403/Forbidden", 1398, "400/Bad Request", 250, "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=zw_jquery.min,one_javascript,zw_jqueryui_autocomplete.min,zw_bootstrap.min,rvmp_zw_javascript,rvmp_similar_cars_slider,rvmp_central_cta&version=143.5", 250, 120, "403/Forbidden", 120, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["https://www.zigwheels.com/upcoming-bikes", 500, 360, "400/Bad Request", 250, "403/Forbidden", 110, "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=rvmp_helpful_review,rvmp_ad_commonscript&version=143.5", 250, 148, "403/Forbidden", 148, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["https://www.zigcdn.com/css/css_bundler_new.html?type=css&files=rvmp_make,rvmp_new_car,rvmp_brand,rvmp_bodytypes_rhs&version=79.1", 500, 237, "403/Forbidden", 237, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=rvmp_make,rvmp_quick_search_mmv,rvmp_n_gpopup,rvmp_rhs_slider&version=143.5", 500, 263, "403/Forbidden", 263, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["https://www.zigcdn.com/css/css_bundler_new.html?type=css&files=rvmp_bootstrap_small.min,rvmp_zw_style_small,rvmp_rhs,onejs,rvmp_community_exit,rvmp_bottom_nav_style,rvmp_icon_zw_style&version=79.1", 250, 115, "403/Forbidden", 115, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["https://www.zigwheels.com/manifest.json", 500, 252, "403/Forbidden", 252, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["https://www.zigcdn.com/js/js_bundler_new.html?type=javascript&files=rvmp_myaccount,rvmp_one_tap_login&version=143.5", 250, 153, "403/Forbidden", 153, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
