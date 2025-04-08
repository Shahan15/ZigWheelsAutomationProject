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
$(document).ready(function() {

    $(".click-title").mouseenter( function(    e){
        e.preventDefault();
        this.style.cursor="pointer";
    });
    $(".click-title").mousedown( function(event){
        event.preventDefault();
    });

    // Ugly code while this script is shared among several pages
    try{
        refreshHitsPerSecond(true);
    } catch(e){}
    try{
        refreshResponseTimeOverTime(true);
    } catch(e){}
    try{
        refreshResponseTimePercentiles();
    } catch(e){}
});


var responseTimePercentilesInfos = {
        data: {"result": {"minY": 162.0, "minX": 0.0, "maxY": 1059.0, "series": [{"data": [[0.0, 162.0], [0.1, 162.0], [0.2, 162.0], [0.3, 162.0], [0.4, 163.0], [0.5, 163.0], [0.6, 163.0], [0.7, 163.0], [0.8, 164.0], [0.9, 164.0], [1.0, 164.0], [1.1, 164.0], [1.2, 164.0], [1.3, 164.0], [1.4, 164.0], [1.5, 164.0], [1.6, 165.0], [1.7, 165.0], [1.8, 165.0], [1.9, 165.0], [2.0, 165.0], [2.1, 165.0], [2.2, 165.0], [2.3, 165.0], [2.4, 165.0], [2.5, 165.0], [2.6, 165.0], [2.7, 165.0], [2.8, 166.0], [2.9, 166.0], [3.0, 166.0], [3.1, 166.0], [3.2, 166.0], [3.3, 166.0], [3.4, 166.0], [3.5, 166.0], [3.6, 166.0], [3.7, 166.0], [3.8, 166.0], [3.9, 166.0], [4.0, 166.0], [4.1, 166.0], [4.2, 167.0], [4.3, 167.0], [4.4, 167.0], [4.5, 167.0], [4.6, 167.0], [4.7, 167.0], [4.8, 167.0], [4.9, 167.0], [5.0, 167.0], [5.1, 167.0], [5.2, 167.0], [5.3, 167.0], [5.4, 167.0], [5.5, 167.0], [5.6, 167.0], [5.7, 167.0], [5.8, 167.0], [5.9, 167.0], [6.0, 168.0], [6.1, 168.0], [6.2, 168.0], [6.3, 168.0], [6.4, 168.0], [6.5, 168.0], [6.6, 168.0], [6.7, 168.0], [6.8, 168.0], [6.9, 168.0], [7.0, 168.0], [7.1, 168.0], [7.2, 169.0], [7.3, 169.0], [7.4, 169.0], [7.5, 169.0], [7.6, 169.0], [7.7, 169.0], [7.8, 170.0], [7.9, 170.0], [8.0, 170.0], [8.1, 170.0], [8.2, 170.0], [8.3, 170.0], [8.4, 170.0], [8.5, 170.0], [8.6, 170.0], [8.7, 170.0], [8.8, 170.0], [8.9, 170.0], [9.0, 170.0], [9.1, 170.0], [9.2, 171.0], [9.3, 171.0], [9.4, 171.0], [9.5, 171.0], [9.6, 171.0], [9.7, 171.0], [9.8, 171.0], [9.9, 171.0], [10.0, 171.0], [10.1, 171.0], [10.2, 171.0], [10.3, 171.0], [10.4, 171.0], [10.5, 171.0], [10.6, 171.0], [10.7, 171.0], [10.8, 172.0], [10.9, 172.0], [11.0, 172.0], [11.1, 172.0], [11.2, 172.0], [11.3, 172.0], [11.4, 172.0], [11.5, 172.0], [11.6, 173.0], [11.7, 173.0], [11.8, 173.0], [11.9, 173.0], [12.0, 173.0], [12.1, 173.0], [12.2, 173.0], [12.3, 173.0], [12.4, 173.0], [12.5, 173.0], [12.6, 173.0], [12.7, 173.0], [12.8, 173.0], [12.9, 173.0], [13.0, 173.0], [13.1, 173.0], [13.2, 173.0], [13.3, 173.0], [13.4, 173.0], [13.5, 173.0], [13.6, 174.0], [13.7, 174.0], [13.8, 174.0], [13.9, 174.0], [14.0, 174.0], [14.1, 174.0], [14.2, 174.0], [14.3, 174.0], [14.4, 174.0], [14.5, 174.0], [14.6, 174.0], [14.7, 174.0], [14.8, 174.0], [14.9, 174.0], [15.0, 174.0], [15.1, 174.0], [15.2, 174.0], [15.3, 174.0], [15.4, 174.0], [15.5, 174.0], [15.6, 175.0], [15.7, 175.0], [15.8, 175.0], [15.9, 175.0], [16.0, 175.0], [16.1, 175.0], [16.2, 175.0], [16.3, 175.0], [16.4, 175.0], [16.5, 175.0], [16.6, 175.0], [16.7, 175.0], [16.8, 175.0], [16.9, 175.0], [17.0, 175.0], [17.1, 175.0], [17.2, 175.0], [17.3, 175.0], [17.4, 175.0], [17.5, 175.0], [17.6, 175.0], [17.7, 175.0], [17.8, 176.0], [17.9, 176.0], [18.0, 176.0], [18.1, 176.0], [18.2, 176.0], [18.3, 176.0], [18.4, 176.0], [18.5, 176.0], [18.6, 176.0], [18.7, 176.0], [18.8, 176.0], [18.9, 176.0], [19.0, 176.0], [19.1, 176.0], [19.2, 176.0], [19.3, 176.0], [19.4, 176.0], [19.5, 177.0], [19.6, 177.0], [19.7, 177.0], [19.8, 177.0], [19.9, 177.0], [20.0, 177.0], [20.1, 177.0], [20.2, 177.0], [20.3, 177.0], [20.4, 177.0], [20.5, 177.0], [20.6, 178.0], [20.7, 178.0], [20.8, 178.0], [20.9, 178.0], [21.0, 178.0], [21.1, 178.0], [21.2, 178.0], [21.3, 178.0], [21.4, 178.0], [21.5, 178.0], [21.6, 178.0], [21.7, 178.0], [21.8, 178.0], [21.9, 178.0], [22.0, 179.0], [22.1, 179.0], [22.2, 179.0], [22.3, 179.0], [22.4, 179.0], [22.5, 179.0], [22.6, 179.0], [22.7, 179.0], [22.8, 179.0], [22.9, 179.0], [23.0, 179.0], [23.1, 179.0], [23.2, 180.0], [23.3, 180.0], [23.4, 180.0], [23.5, 180.0], [23.6, 180.0], [23.7, 180.0], [23.8, 180.0], [23.9, 180.0], [24.0, 181.0], [24.1, 181.0], [24.2, 181.0], [24.3, 181.0], [24.4, 181.0], [24.5, 181.0], [24.6, 181.0], [24.7, 181.0], [24.8, 181.0], [24.9, 181.0], [25.0, 182.0], [25.1, 182.0], [25.2, 182.0], [25.3, 182.0], [25.4, 182.0], [25.5, 182.0], [25.6, 182.0], [25.7, 182.0], [25.8, 182.0], [25.9, 182.0], [26.0, 182.0], [26.1, 182.0], [26.2, 182.0], [26.3, 182.0], [26.4, 182.0], [26.5, 182.0], [26.6, 183.0], [26.7, 183.0], [26.8, 183.0], [26.9, 183.0], [27.0, 183.0], [27.1, 183.0], [27.2, 183.0], [27.3, 183.0], [27.4, 183.0], [27.5, 183.0], [27.6, 183.0], [27.7, 183.0], [27.8, 183.0], [27.9, 183.0], [28.0, 184.0], [28.1, 184.0], [28.2, 184.0], [28.3, 184.0], [28.4, 184.0], [28.5, 184.0], [28.6, 184.0], [28.7, 184.0], [28.8, 185.0], [28.9, 185.0], [29.0, 185.0], [29.1, 185.0], [29.2, 186.0], [29.3, 186.0], [29.4, 186.0], [29.5, 186.0], [29.6, 186.0], [29.7, 186.0], [29.8, 186.0], [29.9, 186.0], [30.0, 187.0], [30.1, 187.0], [30.2, 187.0], [30.3, 187.0], [30.4, 187.0], [30.5, 187.0], [30.6, 187.0], [30.7, 187.0], [30.8, 188.0], [30.9, 188.0], [31.0, 188.0], [31.1, 188.0], [31.2, 189.0], [31.3, 189.0], [31.4, 189.0], [31.5, 189.0], [31.6, 190.0], [31.7, 190.0], [31.8, 191.0], [31.9, 191.0], [32.0, 191.0], [32.1, 191.0], [32.2, 191.0], [32.3, 191.0], [32.4, 192.0], [32.5, 192.0], [32.6, 193.0], [32.7, 193.0], [32.8, 193.0], [32.9, 193.0], [33.0, 193.0], [33.1, 193.0], [33.2, 194.0], [33.3, 194.0], [33.4, 194.0], [33.5, 196.0], [33.6, 196.0], [33.7, 201.0], [33.8, 201.0], [33.9, 201.0], [34.0, 201.0], [34.1, 205.0], [34.2, 205.0], [34.3, 219.0], [34.4, 219.0], [34.5, 219.0], [34.6, 219.0], [34.7, 221.0], [34.8, 221.0], [34.9, 222.0], [35.0, 222.0], [35.1, 224.0], [35.2, 224.0], [35.3, 227.0], [35.4, 227.0], [35.5, 229.0], [35.6, 229.0], [35.7, 240.0], [35.8, 240.0], [35.9, 242.0], [36.0, 242.0], [36.1, 249.0], [36.2, 249.0], [36.3, 250.0], [36.4, 250.0], [36.5, 252.0], [36.6, 252.0], [36.7, 254.0], [36.8, 254.0], [36.9, 255.0], [37.0, 255.0], [37.1, 255.0], [37.2, 255.0], [37.3, 258.0], [37.4, 258.0], [37.5, 258.0], [37.6, 258.0], [37.7, 260.0], [37.8, 260.0], [37.9, 262.0], [38.0, 262.0], [38.1, 262.0], [38.2, 262.0], [38.3, 263.0], [38.4, 263.0], [38.5, 264.0], [38.6, 264.0], [38.7, 265.0], [38.8, 265.0], [38.9, 267.0], [39.0, 267.0], [39.1, 268.0], [39.2, 268.0], [39.3, 268.0], [39.4, 268.0], [39.5, 269.0], [39.6, 269.0], [39.7, 269.0], [39.8, 269.0], [39.9, 270.0], [40.0, 270.0], [40.1, 271.0], [40.2, 271.0], [40.3, 271.0], [40.4, 271.0], [40.5, 272.0], [40.6, 272.0], [40.7, 272.0], [40.8, 272.0], [40.9, 274.0], [41.0, 274.0], [41.1, 274.0], [41.2, 274.0], [41.3, 275.0], [41.4, 275.0], [41.5, 276.0], [41.6, 276.0], [41.7, 276.0], [41.8, 276.0], [41.9, 276.0], [42.0, 276.0], [42.1, 276.0], [42.2, 276.0], [42.3, 277.0], [42.4, 277.0], [42.5, 278.0], [42.6, 278.0], [42.7, 278.0], [42.8, 278.0], [42.9, 280.0], [43.0, 280.0], [43.1, 281.0], [43.2, 281.0], [43.3, 282.0], [43.4, 282.0], [43.5, 285.0], [43.6, 285.0], [43.7, 289.0], [43.8, 289.0], [43.9, 291.0], [44.0, 291.0], [44.1, 312.0], [44.2, 312.0], [44.3, 334.0], [44.4, 334.0], [44.5, 817.0], [44.6, 817.0], [44.7, 817.0], [44.8, 817.0], [44.9, 818.0], [45.0, 818.0], [45.1, 818.0], [45.2, 818.0], [45.3, 820.0], [45.4, 820.0], [45.5, 821.0], [45.6, 821.0], [45.7, 822.0], [45.8, 822.0], [45.9, 822.0], [46.0, 822.0], [46.1, 822.0], [46.2, 822.0], [46.3, 822.0], [46.4, 822.0], [46.5, 822.0], [46.6, 822.0], [46.7, 822.0], [46.8, 822.0], [46.9, 822.0], [47.0, 822.0], [47.1, 823.0], [47.2, 823.0], [47.3, 823.0], [47.4, 823.0], [47.5, 824.0], [47.6, 824.0], [47.7, 824.0], [47.8, 824.0], [47.9, 824.0], [48.0, 824.0], [48.1, 824.0], [48.2, 824.0], [48.3, 825.0], [48.4, 825.0], [48.5, 825.0], [48.6, 825.0], [48.7, 825.0], [48.8, 825.0], [48.9, 825.0], [49.0, 825.0], [49.1, 825.0], [49.2, 825.0], [49.3, 825.0], [49.4, 825.0], [49.5, 825.0], [49.6, 825.0], [49.7, 826.0], [49.8, 826.0], [49.9, 826.0], [50.0, 826.0], [50.1, 826.0], [50.2, 826.0], [50.3, 826.0], [50.4, 826.0], [50.5, 826.0], [50.6, 826.0], [50.7, 828.0], [50.8, 828.0], [50.9, 828.0], [51.0, 828.0], [51.1, 828.0], [51.2, 828.0], [51.3, 829.0], [51.4, 829.0], [51.5, 829.0], [51.6, 829.0], [51.7, 830.0], [51.8, 830.0], [51.9, 830.0], [52.0, 830.0], [52.1, 830.0], [52.2, 830.0], [52.3, 831.0], [52.4, 831.0], [52.5, 831.0], [52.6, 831.0], [52.7, 831.0], [52.8, 831.0], [52.9, 832.0], [53.0, 832.0], [53.1, 832.0], [53.2, 832.0], [53.3, 833.0], [53.4, 833.0], [53.5, 833.0], [53.6, 833.0], [53.7, 834.0], [53.8, 834.0], [53.9, 834.0], [54.0, 834.0], [54.1, 835.0], [54.2, 835.0], [54.3, 835.0], [54.4, 835.0], [54.5, 835.0], [54.6, 835.0], [54.7, 836.0], [54.8, 836.0], [54.9, 836.0], [55.0, 836.0], [55.1, 836.0], [55.2, 836.0], [55.3, 837.0], [55.4, 837.0], [55.5, 837.0], [55.6, 837.0], [55.7, 837.0], [55.8, 837.0], [55.9, 838.0], [56.0, 838.0], [56.1, 838.0], [56.2, 838.0], [56.3, 838.0], [56.4, 838.0], [56.5, 839.0], [56.6, 839.0], [56.7, 839.0], [56.8, 839.0], [56.9, 839.0], [57.0, 839.0], [57.1, 840.0], [57.2, 840.0], [57.3, 840.0], [57.4, 840.0], [57.5, 840.0], [57.6, 840.0], [57.7, 841.0], [57.8, 841.0], [57.9, 841.0], [58.0, 841.0], [58.1, 841.0], [58.2, 841.0], [58.3, 841.0], [58.4, 841.0], [58.5, 842.0], [58.6, 842.0], [58.7, 843.0], [58.8, 843.0], [58.9, 844.0], [59.0, 844.0], [59.1, 844.0], [59.2, 844.0], [59.3, 844.0], [59.4, 844.0], [59.5, 844.0], [59.6, 844.0], [59.7, 844.0], [59.8, 844.0], [59.9, 844.0], [60.0, 844.0], [60.1, 844.0], [60.2, 844.0], [60.3, 844.0], [60.4, 844.0], [60.5, 844.0], [60.6, 844.0], [60.7, 844.0], [60.8, 844.0], [60.9, 844.0], [61.0, 844.0], [61.1, 844.0], [61.2, 844.0], [61.3, 846.0], [61.4, 846.0], [61.5, 846.0], [61.6, 846.0], [61.7, 847.0], [61.8, 847.0], [61.9, 847.0], [62.0, 847.0], [62.1, 847.0], [62.2, 847.0], [62.3, 847.0], [62.4, 847.0], [62.5, 847.0], [62.6, 847.0], [62.7, 847.0], [62.8, 847.0], [62.9, 847.0], [63.0, 847.0], [63.1, 847.0], [63.2, 847.0], [63.3, 847.0], [63.4, 847.0], [63.5, 848.0], [63.6, 848.0], [63.7, 848.0], [63.8, 848.0], [63.9, 848.0], [64.0, 848.0], [64.1, 848.0], [64.2, 848.0], [64.3, 848.0], [64.4, 848.0], [64.5, 848.0], [64.6, 848.0], [64.7, 849.0], [64.8, 849.0], [64.9, 849.0], [65.0, 849.0], [65.1, 849.0], [65.2, 849.0], [65.3, 849.0], [65.4, 849.0], [65.5, 849.0], [65.6, 849.0], [65.7, 850.0], [65.8, 850.0], [65.9, 850.0], [66.0, 850.0], [66.1, 851.0], [66.2, 851.0], [66.3, 851.0], [66.4, 851.0], [66.5, 852.0], [66.6, 852.0], [66.7, 853.0], [66.8, 853.0], [66.9, 853.0], [67.0, 853.0], [67.1, 853.0], [67.2, 853.0], [67.3, 853.0], [67.4, 853.0], [67.5, 854.0], [67.6, 854.0], [67.7, 854.0], [67.8, 854.0], [67.9, 854.0], [68.0, 854.0], [68.1, 854.0], [68.2, 854.0], [68.3, 855.0], [68.4, 855.0], [68.5, 855.0], [68.6, 855.0], [68.7, 856.0], [68.8, 856.0], [68.9, 856.0], [69.0, 856.0], [69.1, 856.0], [69.2, 856.0], [69.3, 856.0], [69.4, 856.0], [69.5, 856.0], [69.6, 856.0], [69.7, 857.0], [69.8, 857.0], [69.9, 857.0], [70.0, 857.0], [70.1, 857.0], [70.2, 857.0], [70.3, 857.0], [70.4, 857.0], [70.5, 858.0], [70.6, 858.0], [70.7, 858.0], [70.8, 858.0], [70.9, 858.0], [71.0, 858.0], [71.1, 859.0], [71.2, 859.0], [71.3, 859.0], [71.4, 859.0], [71.5, 859.0], [71.6, 859.0], [71.7, 859.0], [71.8, 859.0], [71.9, 860.0], [72.0, 860.0], [72.1, 860.0], [72.2, 860.0], [72.3, 860.0], [72.4, 860.0], [72.5, 862.0], [72.6, 862.0], [72.7, 862.0], [72.8, 862.0], [72.9, 862.0], [73.0, 862.0], [73.1, 863.0], [73.2, 863.0], [73.3, 863.0], [73.4, 863.0], [73.5, 863.0], [73.6, 863.0], [73.7, 864.0], [73.8, 864.0], [73.9, 864.0], [74.0, 864.0], [74.1, 865.0], [74.2, 865.0], [74.3, 865.0], [74.4, 865.0], [74.5, 865.0], [74.6, 865.0], [74.7, 865.0], [74.8, 865.0], [74.9, 866.0], [75.0, 866.0], [75.1, 866.0], [75.2, 866.0], [75.3, 867.0], [75.4, 867.0], [75.5, 867.0], [75.6, 867.0], [75.7, 867.0], [75.8, 867.0], [75.9, 868.0], [76.0, 868.0], [76.1, 868.0], [76.2, 868.0], [76.3, 871.0], [76.4, 871.0], [76.5, 871.0], [76.6, 871.0], [76.7, 871.0], [76.8, 871.0], [76.9, 873.0], [77.0, 873.0], [77.1, 873.0], [77.2, 873.0], [77.3, 873.0], [77.4, 873.0], [77.5, 875.0], [77.6, 875.0], [77.7, 875.0], [77.8, 875.0], [77.9, 875.0], [78.0, 875.0], [78.1, 876.0], [78.2, 876.0], [78.3, 876.0], [78.4, 876.0], [78.5, 877.0], [78.6, 877.0], [78.7, 878.0], [78.8, 878.0], [78.9, 879.0], [79.0, 879.0], [79.1, 879.0], [79.2, 879.0], [79.3, 880.0], [79.4, 880.0], [79.5, 882.0], [79.6, 882.0], [79.7, 882.0], [79.8, 882.0], [79.9, 882.0], [80.0, 882.0], [80.1, 883.0], [80.2, 883.0], [80.3, 883.0], [80.4, 883.0], [80.5, 883.0], [80.6, 883.0], [80.7, 885.0], [80.8, 885.0], [80.9, 885.0], [81.0, 885.0], [81.1, 886.0], [81.2, 886.0], [81.3, 886.0], [81.4, 886.0], [81.5, 887.0], [81.6, 887.0], [81.7, 887.0], [81.8, 887.0], [81.9, 889.0], [82.0, 889.0], [82.1, 890.0], [82.2, 890.0], [82.3, 891.0], [82.4, 891.0], [82.5, 893.0], [82.6, 893.0], [82.7, 894.0], [82.8, 894.0], [82.9, 895.0], [83.0, 895.0], [83.1, 895.0], [83.2, 895.0], [83.3, 898.0], [83.4, 898.0], [83.5, 899.0], [83.6, 899.0], [83.7, 900.0], [83.8, 900.0], [83.9, 901.0], [84.0, 901.0], [84.1, 902.0], [84.2, 902.0], [84.3, 902.0], [84.4, 902.0], [84.5, 903.0], [84.6, 903.0], [84.7, 904.0], [84.8, 904.0], [84.9, 904.0], [85.0, 904.0], [85.1, 905.0], [85.2, 905.0], [85.3, 905.0], [85.4, 905.0], [85.5, 905.0], [85.6, 905.0], [85.7, 906.0], [85.8, 906.0], [85.9, 907.0], [86.0, 907.0], [86.1, 908.0], [86.2, 908.0], [86.3, 910.0], [86.4, 910.0], [86.5, 911.0], [86.6, 911.0], [86.7, 912.0], [86.8, 912.0], [86.9, 913.0], [87.0, 913.0], [87.1, 913.0], [87.2, 913.0], [87.3, 915.0], [87.4, 915.0], [87.5, 917.0], [87.6, 917.0], [87.7, 918.0], [87.8, 918.0], [87.9, 918.0], [88.0, 918.0], [88.1, 919.0], [88.2, 919.0], [88.3, 927.0], [88.4, 927.0], [88.5, 928.0], [88.6, 928.0], [88.7, 928.0], [88.8, 928.0], [88.9, 930.0], [89.0, 930.0], [89.1, 931.0], [89.2, 931.0], [89.3, 936.0], [89.4, 936.0], [89.5, 942.0], [89.6, 942.0], [89.7, 945.0], [89.8, 945.0], [89.9, 945.0], [90.0, 945.0], [90.1, 946.0], [90.2, 946.0], [90.3, 946.0], [90.4, 946.0], [90.5, 947.0], [90.6, 947.0], [90.7, 947.0], [90.8, 947.0], [90.9, 947.0], [91.0, 947.0], [91.1, 950.0], [91.2, 950.0], [91.3, 952.0], [91.4, 952.0], [91.5, 952.0], [91.6, 952.0], [91.7, 953.0], [91.8, 953.0], [91.9, 953.0], [92.0, 953.0], [92.1, 953.0], [92.2, 953.0], [92.3, 954.0], [92.4, 954.0], [92.5, 955.0], [92.6, 955.0], [92.7, 955.0], [92.8, 955.0], [92.9, 956.0], [93.0, 956.0], [93.1, 957.0], [93.2, 957.0], [93.3, 959.0], [93.4, 959.0], [93.5, 959.0], [93.6, 959.0], [93.7, 962.0], [93.8, 962.0], [93.9, 963.0], [94.0, 963.0], [94.1, 963.0], [94.2, 963.0], [94.3, 964.0], [94.4, 964.0], [94.5, 967.0], [94.6, 967.0], [94.7, 967.0], [94.8, 967.0], [94.9, 967.0], [95.0, 967.0], [95.1, 968.0], [95.2, 968.0], [95.3, 969.0], [95.4, 969.0], [95.5, 970.0], [95.6, 970.0], [95.7, 974.0], [95.8, 974.0], [95.9, 976.0], [96.0, 976.0], [96.1, 978.0], [96.2, 978.0], [96.3, 979.0], [96.4, 979.0], [96.5, 980.0], [96.6, 980.0], [96.7, 985.0], [96.8, 985.0], [96.9, 985.0], [97.0, 985.0], [97.1, 987.0], [97.2, 987.0], [97.3, 988.0], [97.4, 988.0], [97.5, 995.0], [97.6, 995.0], [97.7, 997.0], [97.8, 997.0], [97.9, 999.0], [98.0, 999.0], [98.1, 1002.0], [98.2, 1002.0], [98.3, 1002.0], [98.4, 1002.0], [98.5, 1004.0], [98.6, 1004.0], [98.7, 1015.0], [98.8, 1015.0], [98.9, 1015.0], [99.0, 1015.0], [99.1, 1023.0], [99.2, 1023.0], [99.3, 1026.0], [99.4, 1026.0], [99.5, 1040.0], [99.6, 1040.0], [99.7, 1049.0], [99.8, 1049.0], [99.9, 1059.0], [100.0, 1059.0]], "isOverall": false, "label": "NewBikes", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
        getOptions: function() {
            return {
                series: {
                    points: { show: false }
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentiles'
                },
                xaxis: {
                    tickDecimals: 1,
                    axisLabel: "Percentiles",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Percentile value in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : %x.2 percentile was %y ms"
                },
                selection: { mode: "xy" },
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentiles"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesPercentiles"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesPercentiles"), dataset, prepareOverviewOptions(options));
        }
};

/**
 * @param elementId Id of element where we display message
 */
function setEmptyGraph(elementId) {
    $(function() {
        $(elementId).text("No graph series with filter="+seriesFilter);
    });
}

// Response times percentiles
function refreshResponseTimePercentiles() {
    var infos = responseTimePercentilesInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimePercentiles");
        return;
    }
    if (isGraph($("#flotResponseTimesPercentiles"))){
        infos.createGraph();
    } else {
        var choiceContainer = $("#choicesResponseTimePercentiles");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesPercentiles", "#overviewResponseTimesPercentiles");
        $('#bodyResponseTimePercentiles .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimeDistributionInfos = {
        data: {"result": {"minY": 2.0, "minX": 100.0, "maxY": 196.0, "series": [{"data": [[300.0, 2.0], [800.0, 196.0], [100.0, 168.0], [200.0, 52.0], [900.0, 72.0], [1000.0, 10.0]], "isOverall": false, "label": "NewBikes", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 1000.0, "title": "Response Time Distribution"}},
        getOptions: function() {
            var granularity = this.data.result.granularity;
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    barWidth: this.data.result.granularity
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " responses for " + label + " were between " + xval + " and " + (xval + granularity) + " ms";
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimeDistribution"), prepareData(data.result.series, $("#choicesResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshResponseTimeDistribution() {
    var infos = responseTimeDistributionInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeDistribution");
        return;
    }
    if (isGraph($("#flotResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var syntheticResponseTimeDistributionInfos = {
        data: {"result": {"minY": 222.0, "minX": 0.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 278.0, "series": [{"data": [[0.0, 222.0]], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [[1.0, 278.0]], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 1.0, "title": "Synthetic Response Times Distribution"}},
        getOptions: function() {
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendSyntheticResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times ranges",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                    tickLength:0,
                    min:-0.5,
                    max:3.5
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    align: "center",
                    barWidth: 0.25,
                    fill:.75
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " " + label;
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            options.xaxis.ticks = data.result.ticks;
            $.plot($("#flotSyntheticResponseTimeDistribution"), prepareData(data.result.series, $("#choicesSyntheticResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshSyntheticResponseTimeDistribution() {
    var infos = syntheticResponseTimeDistributionInfos;
    prepareSeries(infos.data, true);
    if (isGraph($("#flotSyntheticResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerSyntheticResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var activeThreadsOverTimeInfos = {
        data: {"result": {"minY": 11.344000000000005, "minX": 1.74397314E12, "maxY": 15.664000000000017, "series": [{"data": [[1.74397314E12, 15.664000000000017], [1.7439732E12, 11.344000000000005]], "isOverall": false, "label": "Thread Group", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7439732E12, "title": "Active Threads Over Time"}},
        getOptions: function() {
            return {
                series: {
                    stack: true,
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 6,
                    show: true,
                    container: '#legendActiveThreadsOverTime'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                selection: {
                    mode: 'xy'
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : At %x there were %y active threads"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesActiveThreadsOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotActiveThreadsOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewActiveThreadsOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Active Threads Over Time
function refreshActiveThreadsOverTime(fixTimestamps) {
    var infos = activeThreadsOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 3600000);
    }
    if(isGraph($("#flotActiveThreadsOverTime"))) {
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesActiveThreadsOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotActiveThreadsOverTime", "#overviewActiveThreadsOverTime");
        $('#footerActiveThreadsOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var timeVsThreadsInfos = {
        data: {"result": {"minY": 350.23999999999995, "minX": 1.0, "maxY": 845.5, "series": [{"data": [[8.0, 350.23999999999995], [2.0, 845.5], [9.0, 545.4166666666667], [10.0, 511.6562500000003], [11.0, 771.0], [12.0, 632.7058823529412], [3.0, 639.0], [13.0, 468.9736842105262], [14.0, 540.4193548387098], [15.0, 679.5], [16.0, 596.9999999999999], [4.0, 554.6363636363637], [1.0, 820.5], [17.0, 632.2702702702702], [18.0, 611.0754716981133], [19.0, 684.4642857142858], [20.0, 604.4285714285713], [5.0, 723.7], [6.0, 425.45833333333337], [7.0, 538.608695652174]], "isOverall": false, "label": "NewBikes", "isController": false}, {"data": [[13.504000000000001, 576.9920000000003]], "isOverall": false, "label": "NewBikes-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 20.0, "title": "Time VS Threads"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: { noColumns: 2,show: true, container: '#legendTimeVsThreads' },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s: At %x.2 active threads, Average response time was %y.2 ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesTimeVsThreads"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotTimesVsThreads"), dataset, options);
            // setup overview
            $.plot($("#overviewTimesVsThreads"), dataset, prepareOverviewOptions(options));
        }
};

// Time vs threads
function refreshTimeVsThreads(){
    var infos = timeVsThreadsInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTimeVsThreads");
        return;
    }
    if(isGraph($("#flotTimesVsThreads"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTimeVsThreads");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTimesVsThreads", "#overviewTimesVsThreads");
        $('#footerTimeVsThreads .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var bytesThroughputOverTimeInfos = {
        data : {"result": {"minY": 649.9333333333333, "minX": 1.74397314E12, "maxY": 929677.6333333333, "series": [{"data": [[1.74397314E12, 929662.5], [1.7439732E12, 929677.6333333333]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.74397314E12, 649.9333333333333], [1.7439732E12, 649.9333333333333]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7439732E12, "title": "Bytes Throughput Over Time"}},
        getOptions : function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity) ,
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Bytes / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendBytesThroughputOverTime'
                },
                selection: {
                    mode: "xy"
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y"
                }
            };
        },
        createGraph : function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesBytesThroughputOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotBytesThroughputOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewBytesThroughputOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Bytes throughput Over Time
function refreshBytesThroughputOverTime(fixTimestamps) {
    var infos = bytesThroughputOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 3600000);
    }
    if(isGraph($("#flotBytesThroughputOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesBytesThroughputOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotBytesThroughputOverTime", "#overviewBytesThroughputOverTime");
        $('#footerBytesThroughputOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimesOverTimeInfos = {
        data: {"result": {"minY": 474.8840000000002, "minX": 1.74397314E12, "maxY": 679.1, "series": [{"data": [[1.74397314E12, 679.1], [1.7439732E12, 474.8840000000002]], "isOverall": false, "label": "NewBikes", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7439732E12, "title": "Response Time Over Time"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average response time was %y ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Times Over Time
function refreshResponseTimeOverTime(fixTimestamps) {
    var infos = responseTimesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 3600000);
    }
    if(isGraph($("#flotResponseTimesOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesOverTime", "#overviewResponseTimesOverTime");
        $('#footerResponseTimesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var latenciesOverTimeInfos = {
        data: {"result": {"minY": 319.54799999999994, "minX": 1.74397314E12, "maxY": 434.05199999999985, "series": [{"data": [[1.74397314E12, 434.05199999999985], [1.7439732E12, 319.54799999999994]], "isOverall": false, "label": "NewBikes", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7439732E12, "title": "Latencies Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response latencies in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendLatenciesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average latency was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesLatenciesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotLatenciesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewLatenciesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Latencies Over Time
function refreshLatenciesOverTime(fixTimestamps) {
    var infos = latenciesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyLatenciesOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 3600000);
    }
    if(isGraph($("#flotLatenciesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesLatenciesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotLatenciesOverTime", "#overviewLatenciesOverTime");
        $('#footerLatenciesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var connectTimeOverTimeInfos = {
        data: {"result": {"minY": 9.372, "minX": 1.74397314E12, "maxY": 10.076000000000008, "series": [{"data": [[1.74397314E12, 10.076000000000008], [1.7439732E12, 9.372]], "isOverall": false, "label": "NewBikes", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7439732E12, "title": "Connect Time Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getConnectTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average Connect Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendConnectTimeOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average connect time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesConnectTimeOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotConnectTimeOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewConnectTimeOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Connect Time Over Time
function refreshConnectTimeOverTime(fixTimestamps) {
    var infos = connectTimeOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyConnectTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 3600000);
    }
    if(isGraph($("#flotConnectTimeOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesConnectTimeOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotConnectTimeOverTime", "#overviewConnectTimeOverTime");
        $('#footerConnectTimeOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var responseTimePercentilesOverTimeInfos = {
        data: {"result": {"minY": 162.0, "minX": 1.74397314E12, "maxY": 1059.0, "series": [{"data": [[1.74397314E12, 1059.0], [1.7439732E12, 987.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.74397314E12, 163.0], [1.7439732E12, 162.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.74397314E12, 967.0], [1.7439732E12, 888.6]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.74397314E12, 1044.41], [1.7439732E12, 960.47]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.74397314E12, 847.5], [1.7439732E12, 258.0]], "isOverall": false, "label": "Median", "isController": false}, {"data": [[1.74397314E12, 995.9], [1.7439732E12, 909.3499999999999]], "isOverall": false, "label": "95th percentile", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7439732E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Response Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentilesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Response time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentilesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimePercentilesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimePercentilesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Time Percentiles Over Time
function refreshResponseTimePercentilesOverTime(fixTimestamps) {
    var infos = responseTimePercentilesOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 3600000);
    }
    if(isGraph($("#flotResponseTimePercentilesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimePercentilesOverTime", "#overviewResponseTimePercentilesOverTime");
        $('#footerResponseTimePercentilesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var responseTimeVsRequestInfos = {
    data: {"result": {"minY": 177.0, "minX": 2.0, "maxY": 1040.0, "series": [{"data": [[32.0, 262.5], [33.0, 177.0], [2.0, 827.0], [35.0, 270.0], [9.0, 826.0], [11.0, 845.5], [3.0, 912.0], [13.0, 871.0], [4.0, 852.5], [19.0, 852.0], [5.0, 1040.0], [20.0, 860.5], [22.0, 836.0], [6.0, 846.0], [25.0, 834.5], [28.0, 278.0], [30.0, 272.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 35.0, "title": "Response Time Vs Request"}},
    getOptions: function() {
        return {
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Response Time in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: {
                noColumns: 2,
                show: true,
                container: '#legendResponseTimeVsRequest'
            },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median response time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesResponseTimeVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotResponseTimeVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewResponseTimeVsRequest"), dataset, prepareOverviewOptions(options));

    }
};

// Response Time vs Request
function refreshResponseTimeVsRequest() {
    var infos = responseTimeVsRequestInfos;
    prepareSeries(infos.data);
    if (isGraph($("#flotResponseTimeVsRequest"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeVsRequest");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimeVsRequest", "#overviewResponseTimeVsRequest");
        $('#footerResponseRimeVsRequest .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var latenciesVsRequestInfos = {
    data: {"result": {"minY": 164.0, "minX": 2.0, "maxY": 692.0, "series": [{"data": [[32.0, 189.0], [33.0, 164.0], [2.0, 493.0], [35.0, 222.0], [9.0, 494.0], [11.0, 504.5], [3.0, 569.0], [13.0, 524.0], [4.0, 507.0], [19.0, 510.0], [5.0, 692.0], [20.0, 516.5], [22.0, 500.5], [6.0, 504.5], [25.0, 499.0], [28.0, 187.0], [30.0, 214.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 35.0, "title": "Latencies Vs Request"}},
    getOptions: function() {
        return{
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Latency in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: { noColumns: 2,show: true, container: '#legendLatencyVsRequest' },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median Latency time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesLatencyVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotLatenciesVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewLatenciesVsRequest"), dataset, prepareOverviewOptions(options));
    }
};

// Latencies vs Request
function refreshLatenciesVsRequest() {
        var infos = latenciesVsRequestInfos;
        prepareSeries(infos.data);
        if(isGraph($("#flotLatenciesVsRequest"))){
            infos.createGraph();
        }else{
            var choiceContainer = $("#choicesLatencyVsRequest");
            createLegend(choiceContainer, infos);
            infos.createGraph();
            setGraphZoomable("#flotLatenciesVsRequest", "#overviewLatenciesVsRequest");
            $('#footerLatenciesVsRequest .legendColorBox > div').each(function(i){
                $(this).clone().prependTo(choiceContainer.find("li").eq(i));
            });
        }
};

var hitsPerSecondInfos = {
        data: {"result": {"minY": 4.166666666666667, "minX": 1.74397314E12, "maxY": 4.166666666666667, "series": [{"data": [[1.74397314E12, 4.166666666666667], [1.7439732E12, 4.166666666666667]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7439732E12, "title": "Hits Per Second"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of hits / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendHitsPerSecond"
                },
                selection: {
                    mode : 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y.2 hits/sec"
                }
            };
        },
        createGraph: function createGraph() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesHitsPerSecond"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotHitsPerSecond"), dataset, options);
            // setup overview
            $.plot($("#overviewHitsPerSecond"), dataset, prepareOverviewOptions(options));
        }
};

// Hits per second
function refreshHitsPerSecond(fixTimestamps) {
    var infos = hitsPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 3600000);
    }
    if (isGraph($("#flotHitsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesHitsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotHitsPerSecond", "#overviewHitsPerSecond");
        $('#footerHitsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var codesPerSecondInfos = {
        data: {"result": {"minY": 4.166666666666667, "minX": 1.74397314E12, "maxY": 4.166666666666667, "series": [{"data": [[1.74397314E12, 4.166666666666667], [1.7439732E12, 4.166666666666667]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.7439732E12, "title": "Codes Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendCodesPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "Number of Response Codes %s at %x was %y.2 responses / sec"
                }
            };
        },
    createGraph: function() {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesCodesPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotCodesPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewCodesPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Codes per second
function refreshCodesPerSecond(fixTimestamps) {
    var infos = codesPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 3600000);
    }
    if(isGraph($("#flotCodesPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesCodesPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotCodesPerSecond", "#overviewCodesPerSecond");
        $('#footerCodesPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var transactionsPerSecondInfos = {
        data: {"result": {"minY": 4.166666666666667, "minX": 1.74397314E12, "maxY": 4.166666666666667, "series": [{"data": [[1.74397314E12, 4.166666666666667], [1.7439732E12, 4.166666666666667]], "isOverall": false, "label": "NewBikes-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7439732E12, "title": "Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTransactionsPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                }
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTransactionsPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTransactionsPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewTransactionsPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Transactions per second
function refreshTransactionsPerSecond(fixTimestamps) {
    var infos = transactionsPerSecondInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTransactionsPerSecond");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 3600000);
    }
    if(isGraph($("#flotTransactionsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTransactionsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTransactionsPerSecond", "#overviewTransactionsPerSecond");
        $('#footerTransactionsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var totalTPSInfos = {
        data: {"result": {"minY": 4.166666666666667, "minX": 1.74397314E12, "maxY": 4.166666666666667, "series": [{"data": [[1.74397314E12, 4.166666666666667], [1.7439732E12, 4.166666666666667]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.7439732E12, "title": "Total Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTotalTPS"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                },
                colors: ["#9ACD32", "#FF6347"]
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTotalTPS"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTotalTPS"), dataset, options);
        // setup overview
        $.plot($("#overviewTotalTPS"), dataset, prepareOverviewOptions(options));
    }
};

// Total Transactions per second
function refreshTotalTPS(fixTimestamps) {
    var infos = totalTPSInfos;
    // We want to ignore seriesFilter
    prepareSeries(infos.data, false, true);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 3600000);
    }
    if(isGraph($("#flotTotalTPS"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTotalTPS");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTotalTPS", "#overviewTotalTPS");
        $('#footerTotalTPS .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

// Collapse the graph matching the specified DOM element depending the collapsed
// status
function collapse(elem, collapsed){
    if(collapsed){
        $(elem).parent().find(".fa-chevron-up").removeClass("fa-chevron-up").addClass("fa-chevron-down");
    } else {
        $(elem).parent().find(".fa-chevron-down").removeClass("fa-chevron-down").addClass("fa-chevron-up");
        if (elem.id == "bodyBytesThroughputOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshBytesThroughputOverTime(true);
            }
            document.location.href="#bytesThroughputOverTime";
        } else if (elem.id == "bodyLatenciesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesOverTime(true);
            }
            document.location.href="#latenciesOverTime";
        } else if (elem.id == "bodyCustomGraph") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCustomGraph(true);
            }
            document.location.href="#responseCustomGraph";
        } else if (elem.id == "bodyConnectTimeOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshConnectTimeOverTime(true);
            }
            document.location.href="#connectTimeOverTime";
        } else if (elem.id == "bodyResponseTimePercentilesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimePercentilesOverTime(true);
            }
            document.location.href="#responseTimePercentilesOverTime";
        } else if (elem.id == "bodyResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeDistribution();
            }
            document.location.href="#responseTimeDistribution" ;
        } else if (elem.id == "bodySyntheticResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshSyntheticResponseTimeDistribution();
            }
            document.location.href="#syntheticResponseTimeDistribution" ;
        } else if (elem.id == "bodyActiveThreadsOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshActiveThreadsOverTime(true);
            }
            document.location.href="#activeThreadsOverTime";
        } else if (elem.id == "bodyTimeVsThreads") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTimeVsThreads();
            }
            document.location.href="#timeVsThreads" ;
        } else if (elem.id == "bodyCodesPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCodesPerSecond(true);
            }
            document.location.href="#codesPerSecond";
        } else if (elem.id == "bodyTransactionsPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTransactionsPerSecond(true);
            }
            document.location.href="#transactionsPerSecond";
        } else if (elem.id == "bodyTotalTPS") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTotalTPS(true);
            }
            document.location.href="#totalTPS";
        } else if (elem.id == "bodyResponseTimeVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeVsRequest();
            }
            document.location.href="#responseTimeVsRequest";
        } else if (elem.id == "bodyLatenciesVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesVsRequest();
            }
            document.location.href="#latencyVsRequest";
        }
    }
}

/*
 * Activates or deactivates all series of the specified graph (represented by id parameter)
 * depending on checked argument.
 */
function toggleAll(id, checked){
    var placeholder = document.getElementById(id);

    var cases = $(placeholder).find(':checkbox');
    cases.prop('checked', checked);
    $(cases).parent().children().children().toggleClass("legend-disabled", !checked);

    var choiceContainer;
    if ( id == "choicesBytesThroughputOverTime"){
        choiceContainer = $("#choicesBytesThroughputOverTime");
        refreshBytesThroughputOverTime(false);
    } else if(id == "choicesResponseTimesOverTime"){
        choiceContainer = $("#choicesResponseTimesOverTime");
        refreshResponseTimeOverTime(false);
    }else if(id == "choicesResponseCustomGraph"){
        choiceContainer = $("#choicesResponseCustomGraph");
        refreshCustomGraph(false);
    } else if ( id == "choicesLatenciesOverTime"){
        choiceContainer = $("#choicesLatenciesOverTime");
        refreshLatenciesOverTime(false);
    } else if ( id == "choicesConnectTimeOverTime"){
        choiceContainer = $("#choicesConnectTimeOverTime");
        refreshConnectTimeOverTime(false);
    } else if ( id == "choicesResponseTimePercentilesOverTime"){
        choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        refreshResponseTimePercentilesOverTime(false);
    } else if ( id == "choicesResponseTimePercentiles"){
        choiceContainer = $("#choicesResponseTimePercentiles");
        refreshResponseTimePercentiles();
    } else if(id == "choicesActiveThreadsOverTime"){
        choiceContainer = $("#choicesActiveThreadsOverTime");
        refreshActiveThreadsOverTime(false);
    } else if ( id == "choicesTimeVsThreads"){
        choiceContainer = $("#choicesTimeVsThreads");
        refreshTimeVsThreads();
    } else if ( id == "choicesSyntheticResponseTimeDistribution"){
        choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        refreshSyntheticResponseTimeDistribution();
    } else if ( id == "choicesResponseTimeDistribution"){
        choiceContainer = $("#choicesResponseTimeDistribution");
        refreshResponseTimeDistribution();
    } else if ( id == "choicesHitsPerSecond"){
        choiceContainer = $("#choicesHitsPerSecond");
        refreshHitsPerSecond(false);
    } else if(id == "choicesCodesPerSecond"){
        choiceContainer = $("#choicesCodesPerSecond");
        refreshCodesPerSecond(false);
    } else if ( id == "choicesTransactionsPerSecond"){
        choiceContainer = $("#choicesTransactionsPerSecond");
        refreshTransactionsPerSecond(false);
    } else if ( id == "choicesTotalTPS"){
        choiceContainer = $("#choicesTotalTPS");
        refreshTotalTPS(false);
    } else if ( id == "choicesResponseTimeVsRequest"){
        choiceContainer = $("#choicesResponseTimeVsRequest");
        refreshResponseTimeVsRequest();
    } else if ( id == "choicesLatencyVsRequest"){
        choiceContainer = $("#choicesLatencyVsRequest");
        refreshLatenciesVsRequest();
    }
    var color = checked ? "black" : "#818181";
    if(choiceContainer != null) {
        choiceContainer.find("label").each(function(){
            this.style.color = color;
        });
    }
}

