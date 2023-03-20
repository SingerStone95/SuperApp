import os
import zipfile
import logging

bin_path = os.path.abspath(os.path.join(os.getcwd(), 'test', 'bin'))
logging.info(bin_path)
qbsafe_path = os.path.join(bin_path,"QBSafe.dll")
mainifest_path = bin_path = os.path.join(bin_path,'manifest.json')
# 将 QBSafe.dll 和 manifest.json 打包成 zip
file_list = [qbsafe_path,mainifest_path]
tmp_zip_file = bin_path.join('{0508DF1F-2AB6-4fac-A99E-45BBBF24E1E6}.zip')
out_zip_file =  bin_path.join('{0508DF1F-2AB6-4fac-A99E-45BBBF24E1E6}.qrx')
zip_obj = zipfile.ZipFile(tmp_zip_file, 'w',zipfile.ZIP_DEFLATED)
for file in file_list:
    zip_obj.write(file)
zip_obj.close()
os.rename(tmp_zip_file, out_zip_file)
#移除 QBSafe.dll
os.remove(qbsafe_path)