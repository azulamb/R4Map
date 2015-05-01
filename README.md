# Random 4 direction Map library

2次元配列内に次の法則で入った4方向マップをランダムに作るライブラリ。

* 1つの値に上下左右の道(or壁)の情報が格納されている。
* その方向に道がなければ0
* ある場合は方向によって以下の値が決まっている。

    +-- 1 --+
    |       |
    8       2
    |       |
    +-- 4 --+

## サンプル

    [0][6][14][14][10][12]
    [0][7][15][13][0][5]
    [6][11][9][1][0][5]
    [3][10][12][0][0][1]
    [2][12][7][12][0][0]
    [0][3][11][9][0][0]

        [ ]-[ ]-[ ]-[ ]-[ ]
         |   |   |       |
        [ ]-[ ]-[ ]     [ ]
         |   |   |       |
    [ ]-[ ]-[ ] [ ]     [ ]
     |                   |
    [ ]-[ ]-[ ]         [ ]
             |
    [ ]-[ ] [ ]-[ ]
         |   |   |
        [ ]-[ ]-[ ]

## 使い方

ソースコード短いし頑張って。
コメントアウトしたmain関数見て。