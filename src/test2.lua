component = require("component")
io = require("io")
os = require("os")
coroutine = require("coroutine")

gpu = component.gpu
file = io.open("/home/input")
text = file:read("*a")
file:close()
function nextPixel()
    colorI = colorI + 10
    colorJ = colorI + 5
    XI = colorJ + 1
    XJ = XI + 1
    YI = XJ + 1
    YJ = YI + 1
    color = tonumber(string.sub(text, colorI, colorJ),16)
    X = tonumber(string.sub(text, XI, XJ), 16)
    Y = tonumber(string.sub(text, YI, YJ), 16)
end
function quadr(colorf, xf, yf)
    if (tonumber(string.sub(text, colorI, colorJ),16)==tonumber(string.sub(text, colorI+1600, colorJ+1600),16) and tonumber(string.sub(text, colorI, colorJ),16)==tonumber(string.sub(text, colorI+1610, colorJ+1610),16) and tonumber(string.sub(text, colorI, colorJ),16)==tonumber(string.sub(text, colorI+10, colorJ+10),16))  then
        gpu.setBackground(colorf)
        gpu.fill(xf,yf,2,2," ")
        return false
    end
end
function draw(colorf, xf, yf)
    gpu.setBackground(colorf)
    gpu.set(xf, yf, " ")
end
cycle = true
colorI = 1
colorJ = colorI + 5
XI = colorJ + 1
XJ = XI + 1
YI = XJ + 1
YJ = YI + 1
color = tonumber(string.sub(text, colorI, colorJ),16)
X = tonumber(string.sub(text, XI, XJ), 16)
Y = tonumber(string.sub(text, YI, YJ), 16)
while cycle do
    if color and X and Y then
        if quadr(color, X, Y) then
            draw(color, X, Y)
        end
        nextPixel()
    else
        cycle = false
    end
end
while true do coroutine.yield() end