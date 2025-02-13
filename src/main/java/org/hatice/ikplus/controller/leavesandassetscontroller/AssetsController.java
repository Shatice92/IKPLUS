package org.hatice.ikplus.controller.leavesandassetscontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.response.permissionresponse.AssetResponseDto;
import org.hatice.ikplus.dto.request.permissionrequest.CreateAssetRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.service.leavesandassetsservice.AssetsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoints.ASSET)
@RequiredArgsConstructor
public class AssetsController {
	private final AssetsService assetsService;
	
	@GetMapping(LIST)
	public ResponseEntity<BaseResponse<List<AssetResponseDto>>> getAllAssets() {
		return ResponseEntity.ok(BaseResponse.<List<AssetResponseDto>>builder()
		                                     .data(assetsService.findAll())
		                                     .message("All assets listed successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<BaseResponse<AssetResponseDto>> getAssetById(@PathVariable Long id) {
		return ResponseEntity.ok(BaseResponse.<AssetResponseDto>builder()
		                                     .data(assetsService.findById(id))
		                                     .message("Asset found successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@GetMapping("/getByEmployeeId/{employeeId}")
	public ResponseEntity<BaseResponse<List<AssetResponseDto>>> getAssetsByEmployeeId(@PathVariable Long employeeId) {
		return ResponseEntity.ok(BaseResponse.<List<AssetResponseDto>>builder()
		                                     .data(assetsService.getAssetsByEmployeeId(employeeId))
		                                     .message("Assets of employee ID " + employeeId + " listed successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@PostMapping("/save")
	public ResponseEntity<BaseResponse<Boolean>> createAsset(@RequestBody CreateAssetRequestDto dto) {
		assetsService.save(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .data(true)
		                                     .message("Asset created successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BaseResponse<Boolean>> deleteAsset(@PathVariable Long id) {
		assetsService.delete(id);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .data(true)
		                                     .message("Asset deleted successfully.")
		                                     .code(200)
		                                     .success(true)
		                                     .build());
	}
}